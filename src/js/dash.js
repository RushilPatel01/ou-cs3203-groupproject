function correct_balance (balance) {
    let correctBalance = 100;
    let actualBalance = balance;
    if (actualBalance == correctBalance) {
        return true;
    }
    else {
        return false;
    }
}

function checking_or_savings_verify (account) {

    if (account == "Checking") {
        return "Checking";
    }
    else if (account == "Savings") {
        return "Savings";
    }
    else {
        return "Invalid Account";
    }

}

function verify_id (id) {
    if (id == "#deposit_amount" || id == "#deposit_from_account" || id == "#checking_balance_amount" ||
        id == "#savings_balance_amount" || id == "#withdraw_amount" || id == "#withdraw_from_account") 
    {
        return "Valid ID";
    }
    else {
        return "Invalid ID";
    }
}

function add_together (balance, amount) {
    return +balance + +amount;
}

function subtract (balance, amount) {
    return balance - amount;
}

module.exports = { correct_balance, checking_or_savings_verify, verify_id, add_together, subtract }

function deposit() {
    let balance = 100;
    if (correct_balance(balance) != true) {
        alert("Incorrect Balance!");
    }

    let depo_Amount_Id = "#deposit_amount";
    let from_Account_Id = "#deposit_from_account";

    let get_deposit_amount = null;
    let get_from_account = null;
    if (verify_id(depo_Amount_Id) == "Valid ID" && verify_id(from_Account_Id) == "Valid ID") {
        get_deposit_amount = document.querySelector(depo_Amount_Id);
        get_from_account = document.querySelector(from_Account_Id);
    }
    else if ((verify_id(depo_Amount_Id) == "Invalid ID") || verify_id(from_Account_Id) == "Invalid ID")
    {
        alert("Invalid Deposit ID Exists");
        return;
    }

    const checking_or_savings = get_from_account.value;
    if (checking_or_savings_verify(checking_or_savings) == "Checking")
    {
        let h1_checking = null;
        checking_bal = "#checking_balance_amount";
        if (verify_id(checking_bal) == "Valid ID") {
            h1_checking = document.querySelector(checking_bal);
        }
        else if (verify_id(checking_bal) == "Invalid ID") {
            alert("Invalid Deposit ID Exists");
            return;
        }
        h1_checking.innerText = add_together(balance, get_deposit_amount.value);
        get_deposit_amount.value = "";
        balance = balance + get_deposit_amount.value;
    }
    else if (checking_or_savings_verify(checking_or_savings) == "Savings")
    {
        let h1_saving = null;
        saving_bal = "#savings_balance_amount";
        if (verify_id(saving_bal) == "Valid ID") {
            h1_saving = document.querySelector(saving_bal);
        }
        else if (verify_id(saving_bal) == "Invalid ID") {
            alert("Invalid Deposit ID Exists");
            return;
        }
        h1_saving.innerText = add_together(balance, get_deposit_amount.value);
        get_deposit_amount.value = "";
        balance = balance - get_deposit_amount.value;
    }
    else if (checking_or_savings_verify(checking_or_savings) == "Invalid Account")
    {
        alert("Enter Valid Account!");
    }
    }

function withdraw() {
    let balance = 100;
    if (correct_balance(balance) != true) {
        alert("Incorrect Balance!");
    }
    
    let withdraw_Amount_Id = "#withdraw_amount";
    let from_Account_Id = "#withdraw_from_account";

    let get_withdraw_amount = null;
    let get_from_account = null;
    if (verify_id(withdraw_Amount_Id) == "Valid ID" && verify_id(from_Account_Id) == "Valid ID") {
        get_withdraw_amount = document.querySelector(withdraw_Amount_Id);
        get_from_account = document.querySelector(from_Account_Id);
    }
    else if ((verify_id(withdraw_Amount_Id) == "Invalid ID") || verify_id(from_Account_Id) == "Invalid ID")
    {
        alert("Invalid Withdraw ID Exists");
        return;
    }

    const checking_or_savings = get_from_account.value;
    if (checking_or_savings == 'Checking')
    {
        let h1_checking = null;
        checking_bal = "#checking_balance_amount";
        if (verify_id(checking_bal) == "Valid ID") {
            h1_checking = document.querySelector(checking_bal);
        }
        else if (verify_id(checking_bal) == "Invalid ID") {
            alert("Invalid Withdraw ID Exists");
            return;
        }
        h1_checking.innerText = subtract(balance, get_withdraw_amount.value);
        get_withdraw_amount.value = "";
        balance = balance - get_withdraw_amount.value;
    }
    else if (checking_or_savings == 'Savings')
    {
        let h1_saving = null;
        saving_bal = "#savings_balance_amount";
        if (verify_id(saving_bal) == "Valid ID") {
            h1_saving = document.querySelector(saving_bal);
        }
        else if (verify_id(saving_bal) == "Invalid ID") {
            alert("Invalid Deposit ID Exists");
            return;
        }
        h1_saving.innerText = subtract(balance, get_withdraw_amount.value);
        get_withdraw_amount.value = "";
        balance = balance - get_withdraw_amount.value;
    }
    else
    {
        alert("Enter Valid Account!");
    }
}
