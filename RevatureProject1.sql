create table accounts_table(account_id number not null, username varchar(13), pass varchar(13), fName varchar(13), lName varchar(13), account_type varchar(13), 
    reportsto varchar(13),email varchar(50), primary key(account_id));
    
    
create table forms_table(form_id number not null, fName varchar(13), lName varchar(13), grade number, date_compeleted varchar(13), 
    employee_approval varchar(13), benCo_approval varchar(13), dha_approval varchar(13), dsa_approval varchar(13), grades_approval varchar(13),
    form_status varchar(13), submitted_by number not null, description varchar(250), location varchar(50), cost number, reason varchar(250), primary key(form_id));
    
-- generates id values    
create sequence id_seq
minvalue 100
maxvalue 900
increment by 3
cache 3;

-- procedure to approve forms from the employee
create or replace PROCEDURE emp_approval 
(gd in varchar2, id in number)
as
begin
update forms_table set employee_approval = gd where form_id = id;
end emp_approval;






