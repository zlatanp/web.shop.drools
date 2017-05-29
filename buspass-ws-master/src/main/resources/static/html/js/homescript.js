function logout(){
    document.cookie = "username" + '@; Max-Age=0';
    window.location.href="index.html"
}