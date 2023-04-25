const { correct_balance, checking_or_savings_verify, verify_id, add_together, subtract } = require('./dashboard_page_v2')

test('test add balance and amount together', () => {
    expect(add_together(100, 5)).toBe(105)
})

test('test subtract balance from amount', () => {
    expect(subtract(100, 5)).toBe(95)
})

test('test valid id', () => {
    expect(verify_id("#deposit_amount")).toBe("Valid ID")
})

test('test invalid id', () => {
    expect(verify_id("#withdraw__account")).toBe("Invalid ID")
})

test('test another valid id', () => {
    expect(verify_id("#savings_balance_amount")).toBe("Valid ID")
})

test('correctBalance is 100', () => {
    expect(correct_balance(100)).toBe(true)
})

test('correctBalance is not 100', () => {
    expect(correct_balance(50)).toBe(false)
})

test ('is a checking account', () => {
    expect(checking_or_savings_verify("Checking")).toBe("Checking")
})

test ('is a savings account', () => {
    expect(checking_or_savings_verify("Savings")).toBe("Savings")
})

test ('is not a checking or savings account', () => {
    expect(checking_or_savings_verify("Random")).toBe("Invalid Account")
})