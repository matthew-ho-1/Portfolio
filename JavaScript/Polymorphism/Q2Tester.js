function printAccounts(header,list) {
    let div = document.getElementById("output_div");
    let str = "";
    for(let i = 0; i < 10; i++){
        console.log(list[i].toString() + "\n");
        str += list[i].toString() + "\n";
    }
    div.innerHTML += "<pre><code>"  + header + "<br />" + str + "</code></pre>";
}

function main() {
    let accounts = [];
    accounts[0] = new Account(512,216);
    accounts[1] = new Account(1234,521);
    accounts[2] = new Account(21,2992);
    accounts[3] = new Account(10000,12);
    accounts[4] = new Account(1,261261);
    accounts[5] = new PersonalAccount("Joe Shmo",513,216);
    accounts[6] = new PersonalAccount("Harry Potter",1235,999);
    accounts[7] = new PersonalAccount("Luke Skywalker",210,23);
    accounts[8] = new PersonalAccount("Bob Smith",10055,10);
    accounts[9] = new PersonalAccount("Jane Doe",12,26161);
    accounts[0].deposit(200);
    accounts[1].withdraw(21);
    accounts[2].withdraw(-5);
    accounts[3].deposit(-10);
    accounts[4].deposit(2520);
    accounts[5].withdraw(21);
    accounts[6].deposit(25);
    accounts[7].deposit(0);
    accounts[8].withdraw(0);
    accounts[9].deposit(-21);
    printAccounts("All accounts: ",accounts);
    return 0;
}
main();