
function currentUserName(){

    let httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", '/vaccineCandidate/currentLoggedInUserName'); //ei url ta userController e ase
    httpRequest.send();

    httpRequest.onload = function (){
        document.getElementById("currentLoggedInUserName").innerText = httpRequest.responseText;
        // document.getElementById("currentLoggedInUserName").innerText = "method call hocche";
        console.log(httpRequest.responseText);
    }
}
