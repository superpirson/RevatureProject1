-- creating the accounts_table
create table accounts_table(account_id number not null unique, username varchar(13) unique, pass varchar(13), fName varchar(13), lName varchar(13), account_type varchar(13), 
    reportsto varchar(13),email varchar(50), primary key(account_id));
    
-- creating the forms_table    
create table forms_table(form_id number not null unique, fName varchar(13), lName varchar(13), grade number,constraint chk_grade check (grade>0), date_completed date, 
    employee_approval varchar(13), benCo_approval varchar(13), dha_approval varchar(13), dsa_approval varchar(13), grades_approval varchar(13),
    form_status varchar(13), description varchar(250), location varchar(50), cost number,CONSTRAINT chk_cost check (cost>0), reason varchar(250), primary key(form_id), submitted_by number not null,
    CONSTRAINT foreign_key_constraint FOREIGN KEY (submitted_by) REFERENCES accounts_table(account_id));
    
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

create or replace procedure create_grade
(g in nuber)
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
end create_lname;


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





create or replace procedure insert_TRForm
(p_form_id in number, p_fName in varchar, p_lName in varchar, p_grade in number, p_date_completed in date,
p_employee_approval in varchar, p_benCo_approval in varchar, p_dha_approval in varchar, p_dsa_approval in varchar, p_grades_approval in varchar,
p_form_status in varchar, p_description in varchar, p_location in varchar, p_cost in number, p_reason in varchar, p_submit in number)
as
begin
insert into accounts_table 
VALUES (p_form, p_fName, p_lName, p_grade, p_date_completed, p_employee_approval, p_benCo_approval, p_dha_approval, p_dsa_approval, p_grades_approval,
p_form_status, p_description, p_location, p_cost, p_reason, p_submit);
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
create or replace PROCEDURE set_username 
(gd in varchar2, id in number)
as
begin
update accounts_table set username = gd where account_id = id;
end set_username;





-- procedure to set the password
create or replace PROCEDURE set_password
(gd in varchar2, id in number)
as
begin
update accounts_table set pass = gd where account_id = id;
end set_password;




-- procedure to set the first name
create or replace PROCEDURE set_firstname 
(gd in varchar2, id in number)
as
begin
update accounts_table set fname = gd where account_id = id;
end set_firstname;



-- procedure to set the last name
create or replace PROCEDURE set_lastname
(gd in varchar2, id in number)
as
begin
update accounts_table set lname = gd where account_id = id;
end set_lastname;






-- procedure to set the account type
create or replace PROCEDURE set_account_type 
(gd in varchar2, id in number)
as
begin
update accounts_table set account_type = gd where account_id = id;
end set_account_type;





-- procedure to set the reportsto
create or replace PROCEDURE set_reportsto 
(gd in varchar2, id in number)
as
begin
update accounts_table set reportsto = gd where account_id = id;
end set_reportsto;





-- procedure to set the email
create or replace procedure set_email
(gd in varchar2, id in number)
as
begin
update accounts_table set email = gd where account_id = id;
end set_email;

















