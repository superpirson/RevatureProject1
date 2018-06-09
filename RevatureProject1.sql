create table accounts_table(account_id number, username varchar(13), pass varchar(13), fName varchar(13), lName varchar(13), account_type varchar(13), 
    reportsto varchar(13), primary key(account_id));
    
    
create table forms_table( id number not null, fName varchar(13), lName varchar(13), grade number, date_compeleted varchar(13), 
    employee_approval varchar(13), benCo_approval varchar(13), dha_approval varchar(13), dsa_approval varchar(13), grades_approval varchar(13),
    form_status varchar(13), primary key(id), submitted_by number);