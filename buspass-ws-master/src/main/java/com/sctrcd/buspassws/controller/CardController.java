package com.sctrcd.buspassws.controller;

import com.sctrcd.buspassws.model.*;
import com.sctrcd.buspassws.repository.ItemCategoryRepository;
import com.sctrcd.buspassws.repository.ItemRepository;
import com.sctrcd.buspassws.repository.UserRepository;
import com.sctrcd.buspassws.service.DroolsService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private UserRepository userRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    private final DroolsService droolsService;

    @Autowired
    public CardController(DroolsService droolsService) {
        this.droolsService = droolsService;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
    public ItemCountUser addItem(@RequestParam("json") String json, @RequestParam("user") String username) {

        System.out.println(json + username);

        ItemCountUser card = new ItemCountUser(); //All info for drools

        JSONArray arr = new JSONArray(json);
        ArrayList<String> itemCodeCountValue = new ArrayList<String>();
        for (int i = 0; i < arr.length(); i++) {
            itemCodeCountValue.add(arr.get(i).toString());
        }

        User u = userRepository.findByUsername(username);
        card.setU(u);

        for(int i=0;i<itemCodeCountValue.size(); i+=2){

            int count = Integer.parseInt(itemCodeCountValue.get(i+1));
            Item item = itemRepository.findByCode(itemCodeCountValue.get(i));

            boolean wholesale = false;
            String category = item.getCategory();
            List<ItemCategory> allCat = itemCategoryRepository.findAll();
            for (ItemCategory cat : allCat) {
                if(cat.getName().equals(category)){
                    if(cat.isWholesale()){
                        wholesale = true;
                    }else{
                        wholesale = false;
                    }
                }

            }

            ItemCount c = new ItemCount(item, count, wholesale);
            card.getItems().add(c);

        }

        System.out.println( " card: " + card.getItems().size());
        //drools things
        ItemCountUser doneCard = droolsService.getItemCountUser(card);
        System.out.println(" donecard: " + doneCard.getItems().size());
        return doneCard;
    }

}
