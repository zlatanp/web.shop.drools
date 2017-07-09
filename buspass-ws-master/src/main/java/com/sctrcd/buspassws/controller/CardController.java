package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.model.*;
import com.sctrcd.buspassws.repository.*;
import com.sctrcd.buspassws.service.DroolsService;
import com.sctrcd.buspassws.service.DroolsServiceCeoRacun;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zlatan on 2.6.17..
 */
@RestController
@RequestMapping("/cardController")
public class CardController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Autowired
    private ActionEventRepository actionEventRepository;

    private final DroolsService droolsService;

    private final DroolsServiceCeoRacun droolsServiceCeoRacun;


    @Autowired
    public CardController(DroolsService droolsService, DroolsServiceCeoRacun droolsServiceCeoRacun) {
        this.droolsService = droolsService;
        this.droolsServiceCeoRacun = droolsServiceCeoRacun;
    }

    private void CardControll(ItemCountUser card) {
        Date d = card.getU().getDateOfRegistration();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, -2);
        d = cal.getTime();
        card.getU().setDateOfRegistration(d);

        card.setCena(card.getCena() - (card.getCena() * card.getPopust() / 100));

    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    public ItemCountUser addItem(@RequestParam("json") String json, @RequestParam("user") String username) {

        SecureRandom random = new SecureRandom();
        String kod = new BigInteger(130, random).toString(32);

        System.out.println(json + username);

        List<ActionEvent> akcija = actionEventRepository.findAll();
        ActionEvent akcijskiDogadjaj = null;

        for (ActionEvent e : akcija) {
            String od = e.getFrom();
            String doo = e.getTo();

            Date dateOd = null;
            Date datumDo = null;

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateOd = format.parse(od);
                datumDo = format.parse(doo);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            Date datum = new Date();

            if (datum.after(dateOd) && datum.before(datumDo)) {
                akcijskiDogadjaj = e;
            }

        }

        ItemCountUser card = new ItemCountUser(); //All info for drools
        card.setDatum(new Date());

        JSONArray arr = new JSONArray(json);
        ArrayList<String> itemCodeCountValue = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            itemCodeCountValue.add(arr.get(i).toString());
        }

        User u = userRepository.findByUsername(username);
        card.setU(u);

        for (int i = 0; i < itemCodeCountValue.size(); i += 2) {

            int count = Integer.parseInt(itemCodeCountValue.get(i + 1));
            Item item = itemRepository.findByCode(itemCodeCountValue.get(i));

            boolean wholesale = false;
            String category = item.getCategory();
            List<ItemCategory> allCat = itemCategoryRepository.findAll();
            for (ItemCategory cat : allCat) {
                if (cat.getName().equals(category)) {
                    if (cat.isWholesale()) {
                        wholesale = true;
                    } else {
                        wholesale = false;
                    }
                }

            }

            ItemCategory cat = itemCategoryRepository.findByName(item.getCategory());

            ItemCount c = new ItemCount(item, count, wholesale, item.getPrice() * count, false, new Date(), 0, cat.getSuperCategory(), cat.getMaxDiscount());
            card.getItems().add(c);

            card.setPrvaCena(card.getPrvaCena() + c.getPrice());

        }

        System.out.println(" card: " + card.getItems().get(0).getItem().getPrice());

        //Kreiraj 2% za stavku ako je kupovana pre 15 dana

        ArrayList<SviItemi> istorijaKupovina = new ArrayList<SviItemi>();

        List<ItemCountUser> istorija = cardRepository.findAll();
        for (ItemCountUser itemIstorija: istorija) {
            if(itemIstorija.getU().getId().equals(card.getU().getId()))
                for(int j=0; j< itemIstorija.getItems().size(); j++){
                    SviItemi si = new SviItemi(itemIstorija.getItems().get(j).getItem(), itemIstorija.getDatum());
                    istorijaKupovina.add(si);
                }
        }


        for (SviItemi it : istorijaKupovina){
            for(ItemCount i : card.getItems()){
                if(it.getItems().getCode().equals(i.getItem().getCode())){
                    Date datumKupovine = it.getDatum();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(datumKupovine);
                    cal.add(Calendar.DAY_OF_MONTH, 15);
                    datumKupovine = cal.getTime();

                    if(datumKupovine.after(i.getDatum())){
                        i.setDodatnipopust1("-2%");
                        i.setPopust(i.getPopust() + 2);
                    }else{
                        Date datumKupovine2 = it.getDatum();
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(datumKupovine2);
                        cal2.add(Calendar.MONTH, 1);
                        datumKupovine2 = cal2.getTime();

                        if(datumKupovine2.after(i.getDatum())){
                            i.setDodatnipopust1("-1%");
                            i.setPopust(i.getPopust() + 1);
                        }
                    }
                }
            }
        }

        //drools things
        for (int i = 0; i < card.getItems().size(); i++) {

            ItemCount it = card.getItems().get(i);
            droolsService.getItemCount(it, akcijskiDogadjaj);
        }

        double cena = 0;
        for (int i = 0; i < card.getItems().size(); i++) {
            cena += card.getItems().get(i).getPrice();
        }
        card.setCena(cena);
        Date d = card.getU().getDateOfRegistration();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, 2);
        d = cal.getTime();
        card.getU().setDateOfRegistration(d);
        droolsServiceCeoRacun.getItemCount(card);
        card.setKorisnickiPoeni(card.getU().getBuyerProfile().getRewardPoints());
        card.setUnique(kod);

        System.out.println(" donecard: " + card.getItems().get(0).getItem().getPrice() + " popist: " + card.getItems().get(0).getPopust() + " cena " + card.getItems().get(0).getPrice());
        CardControll(card);

        System.out.println("cena na kraj: " + card.getCena() + " popust: " + card.getPopust());
        card.setStatus("PORUCEN");
        cardRepository.save(card);

        return card;
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET, produces = "application/json")
    public void saveItem(@RequestParam("points") String points, @RequestParam("pravaCena") String pravaCena, @RequestParam("unique") String unique) {
        System.out.println(points + " " +pravaCena + " " +unique);

        ItemCountUser itemCard = cardRepository.findByUnique(unique);
        itemCard.setCena(Double.parseDouble(pravaCena));
        cardRepository.save(itemCard);

        //TODO
        //useru setuj nove poene
    }

    @RequestMapping(value = "/getHistory", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<ItemCountUser> getHistory(@RequestParam("username") String username) {
        System.out.println(username);

        List<ItemCountUser> allCards = cardRepository.findAll();
        ArrayList<ItemCountUser> myCards = new ArrayList<ItemCountUser>();

        for (ItemCountUser i: allCards) {
            User u = i.getU();
            if(u.getUsername().equals(username))
                myCards.add(i);
        }

        return myCards;
    }

    @RequestMapping(value = "/getAllRacuni", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<ItemCountUser> getAllRacuni(@RequestParam("username") String username) {
        List<ItemCountUser> allCards = cardRepository.findAll();
        ArrayList<ItemCountUser> Cards = new ArrayList<ItemCountUser>();

        for (ItemCountUser i: allCards) {
                Cards.add(i);
        }

        return Cards;
    }

    @RequestMapping(value = "/otkaziRacun", method = RequestMethod.GET, produces = "application/json")
    public void otkaziRacun(@RequestParam("unique") String unique) {
        ItemCountUser racun = cardRepository.findByUnique(unique);
        racun.setStatus("OTKAZAN");

        cardRepository.save(racun);
    }

    @RequestMapping(value = "/obradiRacun", method = RequestMethod.GET, produces = "application/json")
    public void obradiRacun(@RequestParam("unique") String unique) {
        ItemCountUser racun = cardRepository.findByUnique(unique);

        User u = racun.getU();
        boolean fail = false;
        for (ItemCount c : racun.getItems()) {
            int kolicinaZaKupiit = c.getCount();
            int kolicinaURadnji = c.getItem().getQuantityInShop();

            if (kolicinaZaKupiit > kolicinaURadnji) {
                fail = true;
                break;
            }
        }

        if (fail) {
            racun.setStatus("OTKAZAN");
            cardRepository.save(racun);
        } else {
            for (ItemCount c : racun.getItems()) {
                int kolicinaZaKupiit = c.getCount();
                int kolicinaURadnji = c.getItem().getQuantityInShop();

                c.getItem().setQuantityInShop(kolicinaURadnji - kolicinaZaKupiit);
                itemRepository.save(c.getItem());
            }
            racun.setStatus("REALIZOVAN");
            cardRepository.save(racun);

            //update kupca
            droolsServiceCeoRacun.updateUser(racun, u);
            userRepository.save(u);

        }
    }

    @RequestMapping(value = "/proveriZalihe", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<Item> proveriZalihe() {
        ArrayList<Item> potrebniItemi = new ArrayList<Item>();

        List<Item> sviItemi = itemRepository.findAll();


        //drools things
        for (Item it: sviItemi) {
            droolsServiceCeoRacun.updateItem(it);
        }




        for (Item i: sviItemi) {
            if(i.isNeedMore())
                potrebniItemi.add(i);
        }

        return potrebniItemi;
    }

    @RequestMapping(value = "/poruciKolicinu", method = RequestMethod.GET, produces = "application/json")
    public void poruciKolicinu(@RequestParam("code") String code, @RequestParam("kolicina") String kolicina) {
        Item i = itemRepository.findByCode(code);
        i.setQuantityInShop(i.getQuantityInShop() + Integer.parseInt(kolicina));
        itemRepository.save(i);
    }

}

