drop table forms_table;
drop table accounts_table;
-- creating the accounts_table
create table accounts_table(account_id number not null, username varchar(13) unique, pass varchar(13), fName varchar(13), lName varchar(13), account_type varchar(13), 
    reportsto varchar(13),email varchar(50), primary key(account_id));
    
-- creating the forms_table    
create  table forms_table(form_id number not null, fName varchar(13), lName varchar(13), grade varchar(13), date_completed date, 
    employee_approval varchar(13), benCo_approval varchar(13), dha_approval varchar(13), dsa_approval varchar(13), grades_approval varchar(13),
    form_status varchar(13), description varchar(250), location varchar(50), cost number,CONSTRAINT chk_cost check (cost>0), reason_denial varchar(250), reason_change varchar(250), 
    reason_reimburse varchar(250),event_type varchar(13),forms blob ,primary key(form_id), submitted_by number not null,
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


--insert into forms_table values(form_id_seq.nextval, 'Joe','Coppola', 'A+', date '05-09-18','Yes', 'Yes', 'Yes','Yes', 'Yes',
--    'pending', 'revature', 'tampa', 250, null, null, 'tranining','bootcamp', 103);

    


insert into forms_table values(90, 'Super-Joe','Coppola', 'A*(A+)', date '05-09-18','Yes', 'Yes', 'Yes','Yes', 'Yes',
    'pending', 'revature', 'tampa', 250, null, null, 'tranining','bootcamp',null ,103);





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
update forms_table set forms = f where form_id = id;
end w_blob;








-- function to calculate the percentage of the reimbursement
create or replace function calc_reimbursement_percent
(f_id in number, percentage in number)
RETURN NUMBER AS 
reimbursement_total number(10, 2);
begin
select cost into reimbursement_total
from forms_table where form_id = f_id;
return (reimbursement_total*percentage);
end;
/

select calc_reimbursement_percent(90, .80) from dual;



