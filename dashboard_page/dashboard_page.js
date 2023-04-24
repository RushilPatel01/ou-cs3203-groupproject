function deposit() {
    let balance = 5000;
    const get_deposit_amount = document.querySelector("#deposit_amount");
    const get_from_account = document.querySelector("#deposit_from_account");

    const checking_or_savings = get_from_account.value;
    if (checking_or_savings == 'Checking')
    {
        const h1_checking = document.querySelector("#deposit_checking_balance_amount");
        h1_checking.innerText = +balance + +get_deposit_amount.value;
        get_deposit_amount.value = "";
        balance = balance + get_deposit_amount.value;
    }
    else if (checking_or_savings == 'Savings')
    {
        const h1_saving = document.querySelector("#deposit_saving_balance_amount");
        h1_saving.innerText = +balance + +get_deposit_amount.value;
        get_deposit_amount.value = "";
        balance = balance - get_deposit_amount.value;
    }
    else
    {
        alert("Enter Valid Account!");
    }
    }

function withdraw() {
    const get_withdraw_amount = document.querySelector("#withdraw_amount");
    const get_from_account = document.querySelector("#from_account");

    const checking_or_savings = get_from_account.value;
    if (checking_or_savings == 'Checking')
    {
        const h1_checking = document.querySelector("#checking_balance_amount");
        h1_checking.innerText = balance - get_withdraw_amount.value;
        get_withdraw_amount.value = "";
        balance = balance - get_withdraw_amount.value;
    }
    else if (checking_or_savings == 'Savings')
    {
        const h1_saving = document.querySelector("#saving_balance_amount");
        h1_saving.innerText = balance - get_withdraw_amount.value;
        get_withdraw_amount.value = "";
        balance = balance - get_withdraw_amount.value;
    }
    else
    {
        alert("Enter Valid Account!");
    }

    }