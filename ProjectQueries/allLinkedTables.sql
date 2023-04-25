/*
create table Accounts(
AccountID bigint IDENTITY(100000,1),
Balance float,
id varchar(8) Foreign Key References dbo.Login(Username),
Primary Key(AccountID))
*/


select * from Accounts

/*
create Table Cards(
AccountID bigint Identity (100000,1),
pin Int not null, SecurityCode int not null,
Expiration varchar(5) not null,
cardID bigint not null,
Primary Key(cardID),
Foreign Key(AccountID) References [dbo].[Accounts](AccountID))
*/
/*
create table Transactions(
TransactionID bigint Identity (0,1) not null,
FromID bigINt not null,
ToID bigint not null,
[Value] float not null,
[Date] datetime NOT NULL CONSTRAINT DF_Status_DateTransact DEFAULT (getdate()),
Primary Key (TransactionID),
Foreign Key(FromID) References Accounts(AccountID),
Foreign Key(ToID) References Accounts(AccountID))
*/