drop table forms_table;
drop table accounts_table;

-- creating the accounts_table
create table accounts_table(account_id number not null, username varchar(1000) unique, pass varchar(1000), fName varchar(1000), lName varchar(1000), account_type varchar(1000), 
    reportsto varchar(1000),email varchar(1000), primary key(account_id));
    
    
-- creating the forms_table    
create  table forms_table(form_id number not null, fName varchar(1000), lName varchar(1000), grade varchar(1000), date_completed date, 
    employee_approval varchar(1000), benCo_approval varchar(1000), dha_approval varchar(1000), dsa_approval varchar(1000), grades_approval varchar(1000),
    form_status varchar(1000), description varchar(1000), location varchar(1000), cost number,CONSTRAINT chk_cost check (cost>=0 and cost<1000), reason_denial varchar(1000), reason_change varchar(1000), 
    reason_reimburse varchar(1000),event_type varchar(1000),files blob ,primary key(form_id), submitted_by number not null,
    CONSTRAINT foreign_key_constraint FOREIGN KEY (submitted_by) REFERENCES accounts_table(account_id));
    
    
    drop sequence account_id_seq ;
    drop sequence form_id_seq;
    
-- generates account_id values
create sequence account_id_seq
minvalue 100
maxvalue 900
increment by 3
cache 3;

-- generates form_id values
create sequence form_id_seq
minvalue 100
maxvalue 900
increment by 7
cache 7;



insert into accounts_table values(account_id_seq.nextval, 'rafaeltx', 'awesomesauce', 'Rafael', 'Mariano', 'DHA', 'DSA', 'rafael@idk.com');   
insert into accounts_table values(account_id_seq.nextval, 'alexGee', 'alexpatton', 'Alex', 'Patton', 'Employee', 'DHA', 'alex@idk.com'); 
insert into accounts_table values(account_id_seq.nextval, 'admin', 'admin', 'admin', 'admin', 'Employee', 'DHA', 'alex@idk.com');   




--create view view_account as
--select * 
--from accounts_table;
--
--select * from view_account;






--insert into forms_table values(form_id_seq.nextval, 'Joe','Coppola', 'A+', date '05-09-18','Yes', 'Yes', 'Yes','Yes', 'Yes',
--    'pending', 'revature', 'tampa', 250, null, null, 'tranining','bootcamp', 103);

    


insert into forms_table values(90, 'Super-Joe','Coppola', 'A*(A+)', date '05-09-18','Yes', 'Yes', 'Yes','Yes', 'Yes',
    'pending', 'revature', 'tampa', 250, null, null, 'tranining','bootcamp',null ,103);
    
insert into forms_table values(93, 'Goku','Supersayan', 'B*(B+)', date '01-03-18','No', 'Yes', 'No','Yes', 'Yes',
    'approved', 'dragon ball z', 'new york', 300, null, null, 'tranining','bootcamp',null ,106);
    
insert into forms_table values(96, 'Matt','Knighten', 'F*(F)', date '06-06-06','No', 'Yes', 'No','Yes', 'Yes',
    'approved', 'real marine', 'new york', 350, null, null, 'tranining','bootcamp',null ,106);


/*              procedures for the forms_table          */

/* ---------------------------------------------FIRST NAME-------------------------------------------------------- */
-- procedure to insert the first name
create or replace procedure create_fname
(firstN in varchar2)
as
begin
insert into forms_table(fname) values (firstN);
end create_fname;



--procedure to update the first name
create or replace PROCEDURE update_fname 
(firstN in varchar2, id in number)
as
begin
update forms_table set fname = firstN where form_id = id;
end update_fname;

/* -------------------------------------------LAST NAME---------------------------------------------------------- */

-- procedure to set the last name
create or replace procedure create_lname
(lastN in varchar2)
as
begin
insert into forms_table(lname) values (lastN);
end create_lname;




-- procedure to set the last name
create or replace PROCEDURE update_lname 
(lastN in varchar2, id in number)
as
begin
update forms_table set lname = lastN where form_id = id;
end update_lname;



/* ------------------------------------------- GRADE ---------------------------------------------------------- */

create or replace procedure create_lname
(g in number)
as
begin
insert into forms_table(grade) values (g);
end create_lname;



-- procedure to set the grade
create or replace PROCEDURE update_grade 
(gd in number, id in number)
as
begin
update forms_table set grade = gd where form_id = id;
end update_grade;



/* -------------------------------------------DATE COMPLETED---------------------------------------------------------- */

create or replace procedure create_date_completed
(dat in date)
as
begin
insert into forms_table(date_completed) values (dat);
end create_date_completed;


-- procedure to set the date_completed
create or replace PROCEDURE update_date_complete 
(gd in date, id in number)
as
begin
update forms_table set date_completed = gd where form_id = id;
end update_date_complete;



/* -------------------------------------------EMPLOYEE APPROVAL---------------------------------------------------------- */

create or replace procedure create_emp_approval
(d in varchar2)
as
begin
insert into forms_table(employee_approval) values (d);
end create_emp_approval;


-- procedure to approve forms from the employee
--(yes/no, form_id)
create or replace PROCEDURE update_emp_approval 
(gd in varchar2, id in number)
as
begin
update forms_table set employee_approval = gd where form_id = id;
end update_emp_approval;



/* -------------------------------------------BENCO APPROVAL---------------------------------------------------------- */

create or replace procedure create_benco_approval
(d in varchar2)
as
begin
insert into forms_table(benco_approval) values (d);
end create_benco_approval;


-- procedure to approve forms from BenCo
--(yes/no, form_id)
create or replace PROCEDURE update_benco_app
(gd in varchar2, id in number)
as
begin
update forms_table set benco_approval = gd where form_id = id;
end update_benco_app;




/* ------------------------------------------- DHA APPROVAL ---------------------------------------------------------- */

create or replace procedure create_dha_approval
(d in varchar2)
as
begin
insert into forms_table(DHA_APPROVAL) values (d);
end create_dha_approval;


-- procedure to approve forms BY DHA
--(yes/no, form_id)
create or replace PROCEDURE update_dha_app 
(gd in varchar2, id in number)
as
begin
update forms_table set DHA_approval = gd where form_id = id;
end update_dha_app;




/* ------------------------------------------- DSA APPROVAL ---------------------------------------------------------- */

create or replace procedure create_dsa_approval
(d in varchar2)
as
begin
insert into forms_table(dsa_approval) values (d);
end create_dsa_approval;


-- procedure to approve forms BY DSA
--(yes/no, form_id)
create or replace PROCEDURE update_DSA_app 
(gd in varchar2, id in number)
as
begin
update forms_table set DSA_approval = gd where form_id = id;
end update_dsa_app;






/* -------------------------------------------GRADES APPROVAL---------------------------------------------------------- */

create or replace procedure create_grades_approval
(d in varchar2)
as
begin
insert into forms_table(grades_approval) values (d);
end create_grades_approval;


-- procedure to approve grades
--(yes/no, form_id)
create or replace PROCEDURE update_grades_app
(gd in varchar2, id in number)
as
begin
update forms_table set GRADES_approval = gd where form_id = id;
end update_grades_app;







/* -------------------------------------------FORM STATUS---------------------------------------------------------- */

create or replace procedure create_form_status
(d in varchar2)
as
begin
insert into forms_table(form_status) values (d);
end create_form_status;


-- procedure to set for status
--(pending/approved/denied, form_id)
create or replace PROCEDURE update_form_stat
(gd in varchar2, id in number)
as
begin
update forms_table set form_status = gd where form_id = id;
end update_form_stat;



/* ------------------------------------------- SUBMITTED BY ---------------------------------------------------------- */
CREATE OR REPLACE PROCEDURE create_SUBMITTED_BY
(X IN NUMBER)
AS
BEGIN
INSERT INTO FORMS_TABLE(SUBMITTED_BY) VALUES(X);
END create_SUBMITTED_BY;


-- procedure to update the submitted by column
CREATE OR REPLACE PROCEDURE update_SUBMITTED_BY
(X IN NUMBER, ID IN NUMBER)
AS
BEGIN
UPDATE FORMS_TABLE SET SUBMITTED_BY = X WHERE FORM_ID = ID;
END update_SUBMITTED_BY;



/* ------------------------------------------- DESCRIPTION ---------------------------------------------------------- */

create or replace procedure create_description
(d in varchar2)
as
begin
insert into forms_table(description) values (d);
end create_description;


-- procedure to write the description
--("description", form_id)
create or replace PROCEDURE update_description
(gd in varchar2, id in number)
as
begin
update forms_table set description = gd where form_id = id;
end update_description;







/* ------------------------------------------- LOCATION ---------------------------------------------------------- */

create or replace procedure create_location
(d in varchar2)
as
begin
insert into forms_table(location) values (d);
end create_location;


--procedure to set the location
--("location", form_id)
create or replace procedure update_location
(loc in varchar2, id in number)
as
begin 
update forms_table set location = loc where form_id = id;
end update_location;




/* ------------------------------------------- COST ---------------------------------------------------------- */

create or replace procedure create_cost
(d in number)
as
begin
insert into forms_table(cost) values (d);
end create_cost;



--procedure to set the cost
--(23.99, forms_id)
create or replace procedure update_cost
(cos in number, id in number)
as
begin 
update forms_table set cost = cos where form_id = id;
end update_cost;




/* -------------------------------------------REASON---------------------------------------------------------- */

create or replace procedure create_reason
(d in varchar2)
as
begin
insert into forms_table(reason) values (d);
end create_reason;


--procedure to set the reason
--("reason", forms_id)
create or replace procedure update_reason
(cos in varchar2, id in number)
as
begin 
update forms_table set reason = cos where form_id = id;
end update_reason;




 
 





create or replace procedure insert_trform
(p_id in number, p_first in varchar2, p_last in varchar2, p_grade in varchar2, p_date_completed in date, p_emp_app in varchar2, p_benco_app in varchar2,
p_dha_app in varchar2, p_dsa_app in varchar2, p_grades_app in varchar2, p_form_status in varchar2, p_description in varchar2, p_location in varchar2, 
p_cost in varchar2, p_reason_denial in varchar2, p_reason_change in varchar2, p_reason_reimbursement in varchar2, p_event_type in varchar2,p_blob in blob ,p_submitted_by in number)
as
begin
insert into forms_table
values (form_id_seq.nextval, p_first, p_last, p_grade, p_date_completed, p_emp_app, p_benco_app, p_dha_app, p_dsa_app, p_grades_app,
p_form_status, p_description, p_location, p_cost, p_reason_denial, p_reason_change, p_reason_reimbursement, p_event_type,p_blob ,p_submitted_by);
end insert_trform;



create or replace function trform_new(submittedBy number) return number as 
p_id  number;

begin
select form_id_seq.nextval  into p_id from dual;
insert into forms_table
values (p_id, '','', '', date '05-09-18','F', 'No', 'No','No', 'No',
    '', '', '', 0, null, null, '','',null ,submittedBy);
    return(p_id);
end trform_new;





/*                      procedures for accounts_table               */


create or replace procedure insert_trform_accounts_table
(acc_id in number, usern in varchar2, pswrd in varchar2, firstn in varchar2, lastn in varchar2, acnt_type in varchar2, 
reports in varchar2, eml in varchar2)
as 
begin
insert into accounts_table
values (account_id_seq.nextval, usern, pswrd, firstn, lastn, acnt_type, reports, eml);
end insert_trform_accounts_table;


-- procedure to set the username
create or replace PROCEDURE update_username 
(gd in varchar2, id in number)
as
begin
update accounts_table set username = gd where account_id = id;
end update_username;





-- procedure to set the password
create or replace PROCEDURE update_password
(gd in varchar2, id in number)
as
begin
update accounts_table set pass = gd where account_id = id;
end update_password;




-- procedure to set the first name
create or replace PROCEDURE update_firstname 
(gd in varchar2, id in number)
as
begin
update accounts_table set fname = gd where account_id = id;
end update_firstname;



-- procedure to set the last name
create or replace PROCEDURE update_lastname
(gd in varchar2, id in number)
as
begin
update accounts_table set lname = gd where account_id = id;
end update_lastname;






-- procedure to set the account type
create or replace PROCEDURE update_account_type 
(gd in varchar2, id in number)
as
begin
update accounts_table set account_type = gd where account_id = id;
end update_account_type;





-- procedure to set the reportsto
create or replace PROCEDURE update_reportsto 
(gd in varchar2, id in number)
as
begin
update accounts_table set reportsto = gd where account_id = id;
end update_reportsto;





-- procedure to set the email
create or replace procedure update_email
(gd in varchar2, id in number)
as
begin
update accounts_table set email = gd where account_id = id;
end update_email;


/*                  delete procedures               */

-- to delete an account from the account table
CREATE OR REPLACE PROCEDURE DELETE_ACCOUNT 
(id in number)
AS 
BEGIN
  delete from accounts_table where account_id = id;
END DELETE_ACCOUNT;


-- to delete a form in the forms_table
CREATE OR REPLACE PROCEDURE DELETE_FORM
(id in number)
AS 
BEGIN
  delete from forms_table where form_id = id;
END DELETE_form;









create or replace procedure view_forms
(vc_cursor out sys_refcursor, a_id in number, report_to in varchar2)
as
begin
    open vc_cursor for 
    select * from forms_table 
    join accounts_table 
    on forms_table.submitted_by = accounts_table.account_id
    where account_id = a_id and reportsto = report_to;
    commit;
end;

variable rc refcursor;
exec view_forms(:rc, 103, 'DHA');
print rc;






-- procedure to insert the blob file
create or replace procedure w_blob
(f in blob, id in number)
as
begin
update forms_table set files = f where form_id = id;
end w_blob;








-- function to calculate the percentage of the reimbursement
create or replace function calc_reimburstment_percent
(f_id in number, percentage in number)
RETURN NUMBER AS 
reimburstment_total number(10, 2);
begin
select cost into reimburstment_total
from forms_table where form_id = f_id;
return (reimburstment_total*percentage);
end;
/

select calc_reimburstment_percent(90, .80) from dual;


create or replace function calculate_reimburse
(f_id in number, e_type in varchar2)
return number as 
reimburstment_total number(10,2);
begin
if e_type = 'University Course' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*.80);
elsif e_type = 'Seminar' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*.60);
elsif e_type = 'Certification Preparation Class' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*.75);
elsif e_type = 'Certification' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*1);
elsif e_type = 'Technical Training' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*.90);
elsif e_type = 'Other' then
    select cost into reimburstment_total
    from forms_table where form_id = f_id;
    return (reimburstment_total*.30);
    
    end if;
return (0);
    
end;
/



create or replace function available_reimburstement
(id in number, percentage in number)
return number as
a_reimburstment number(10,2);
begin
select cost into a_reimburstment
from forms_table where form_id = id;
return (1000-calc_reimburstment_percent(id, percentage)-(calc_reimburstment_percent(id, percentage)));
end;
/

