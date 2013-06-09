-- Drop previous version of the schema; might throw errors, if try to delete nonexisting objects,
-- but it is not a problem

drop table products cascade constraints;
drop table classes cascade constraints;
drop table methods cascade constraints;
drop table fields cascade constraints;
drop table class_definition_dependencies cascade constraints;
drop table field_reference_dependencies cascade constraints;
drop table method_call_dependencies cascade constraints;
drop table inheritance_dependencies cascade constraints;
drop table override_dependencies cascade constraints;

drop sequence products_seq;
drop sequence classes_seq;
drop sequence methods_seq;
drop sequence fields_seq;
drop sequence cddep_seq;
drop sequence frdep_seq;
drop sequence mcdep_seq;
drop sequence indep_seq;
drop sequence ovdep_seq;

--drop trigger products_trigger_idgen;
--drop trigger classes_trigger_idgen;

-- name:  products 
-- alias: products
create table products (
    product_id integer,
    name varchar2(100),
    versions varchar2(1000),
    containing_folder varchar2(100),
	product_path varchar2(1000)
);
alter table products add constraint products_pk primary key (product_id);
create sequence products_seq;
create or replace trigger products_trigger_idgen 
    before insert on products 
    for each row
    when (new.product_id is null) begin
        select products_seq.nextval
        into :new.product_id
        from dual;
    end;
/

-- name:  classes
-- alias: classes
create table classes( 
    class_id integer,
    simple_name varchar2(200),
    package_name varchar2(500),
    super_class_name varchar2(1000),
    interface_names varchar2(3000),
    fully_qualified_name varchar2(4000),
	versions varchar2(1000),
    product_id integer
);
alter table classes add constraint classes_pk primary key (class_id);
create sequence classes_seq;
create or replace trigger classes_trigger_idgen 
    before insert on classes 
    for each row
    when (new.class_id is null) begin
        select classes_seq.nextval
        into :new.class_id
        from dual;
    end;
/
alter table classes add constraint classes_products_fk foreign key (product_id) 
    references products(product_id) on delete cascade enable;
create index classes_product_id_I on classes (product_id); 

create or replace trigger classes_fqname_gen
	before insert on classes 
	for each row
	when(new.fully_qualified_name is null) begin
		select concat(:new.package_name, concat('.', :new.simple_name))
		into :new.fully_qualified_name
	from dual;
	end;
/ 
 
-- name:  methods
-- alias: methods
create table methods ( 
    method_id integer,
    signature varchar2(4000),
	is_static char(1), 
	versions varchar2(1000),
	class_id integer
);
alter table methods add constraint methods_pk primary key (method_id);
create sequence methods_seq;
create or replace trigger methods_trigger_idgen 
    before insert on methods 
    for each row
    when (new.method_id is null) begin
        select methods_seq.nextval
        into :new.method_id
        from dual;
    end;
/
alter table methods add constraint methods_classes_fk foreign key (class_id) 
    references classes(class_id) on delete cascade enable;
create index methods_class_id_I on methods (class_id); 

-- name:  fields
-- alias: fields
create table fields ( 
    field_id integer,
    signature varchar2(4000),
	versions varchar2(1000),
    class_id integer
);
alter table fields add constraint fields_pk primary key (field_id);
create sequence fields_seq;
create or replace trigger fields_trigger_idgen 
    before insert on fields 
    for each row
    when (new.field_id is null) begin
        select fields_seq.nextval
        into :new.field_id
        from dual;
    end;
/
alter table fields add constraint fields_classes_fk foreign key (class_id) 
    references classes(class_id) on delete cascade enable;
create index fields_class_id_I on fields (class_id); 

-- name: class_definition_dependencies
-- alias: cddep
create table class_definition_dependencies (
   cddep_id integer,
   from_class_id integer,
   to_class_id integer
);
alter table class_definition_dependencies add constraint cddep_pk primary key (cddep_id);
create sequence cddep_seq;
create or replace trigger cddep_trigger_idgen 
    before insert on class_definition_dependencies 
    for each row
    when (new.cddep_id is null) begin
        select cddep_seq.nextval
        into :new.cddep_id
        from dual;
    end;
/
alter table class_definition_dependencies add constraint cddep_classes_from_fk 
    foreign key (from_class_id) references classes(class_id) on delete cascade enable;
alter table class_definition_dependencies add constraint cddep_classes_to_fk 
    foreign key (to_class_id) references classes(class_id) on delete cascade enable;    
create index cddep_from_class_id_I on class_definition_dependencies (from_class_id); 
create index cddep_to_class_id_I on class_definition_dependencies (to_class_id); 

-- name: field_ref_dependencies
-- alias: frdep
create table field_reference_dependencies (
   frdep_id integer,
   from_method_id integer,
   to_field_id integer
);
alter table field_reference_dependencies add constraint drdep_pk primary key (frdep_id);
create sequence frdep_seq;
create or replace trigger frdep_trigger_idgen 
    before insert on field_reference_dependencies 
    for each row
    when (new.frdep_id is null) begin
        select frdep_seq.nextval
        into :new.frdep_id
        from dual;
    end;
/
alter table field_reference_dependencies add constraint frdep_methods_fk 
    foreign key (from_method_id) references methods(method_id) on delete cascade enable;
alter table field_reference_dependencies add constraint frdep_fields_fk 
    foreign key (to_field_id) references fields(field_id) on delete cascade enable;    
create index frdep_from_method_id_I on field_reference_dependencies (from_method_id); 
create index frdep_to_field_id_I on field_reference_dependencies (to_field_id); 

-- name: method_call_dependencies
-- alias: mcdep
create table method_call_dependencies (
   mcdep_id integer,
   from_method_id integer,
   to_method_id integer
);
alter table method_call_dependencies add constraint mcdep_pk primary key (mcdep_id);
create sequence mcdep_seq;
create or replace trigger mcdep_trigger_idgen 
    before insert on method_call_dependencies 
    for each row
    when (new.mcdep_id is null) begin
        select mcdep_seq.nextval
        into :new.mcdep_id
        from dual;
    end;
/
alter table method_call_dependencies add constraint mcdep_from_methods_fk 
    foreign key (from_method_id) references methods(method_id) on delete cascade enable;
alter table method_call_dependencies add constraint mcdep_to_methods_fk 
    foreign key (to_method_id) references methods(method_id) on delete cascade enable;    
create index mcdep_from_method_id_I on method_call_dependencies (from_method_id); 
create index mcdep_to_method_id_I on method_call_dependencies (to_method_id); 

-- name: inheritance_dependencies
-- alias: indep
create table inheritance_dependencies (
   indep_id integer,
   from_class_id integer,
   to_class_id integer
);
alter table inheritance_dependencies add constraint indep_pk primary key (indep_id);
create sequence indep_seq;
create or replace trigger indep_trigger_idgen 
    before insert on inheritance_dependencies 
    for each row
    when (new.indep_id is null) begin
        select indep_seq.nextval
        into :new.indep_id
        from dual;
    end;
/
alter table inheritance_dependencies add constraint indep_classes_from_fk 
    foreign key (from_class_id) references classes(class_id) on delete cascade enable;
alter table inheritance_dependencies add constraint indep_classes_to_fk 
    foreign key (to_class_id) references classes(class_id) on delete cascade enable;    
create index indep_from_class_id_I on inheritance_dependencies (from_class_id); 
create index indep_to_class_id_I on inheritance_dependencies (to_class_id); 

-- name: override_dependencies
-- alias: ovdep
create table override_dependencies (
   ovdep_id integer,
   from_method_id integer,
   to_method_id integer
);
alter table override_dependencies add constraint ovdep_pk primary key (ovdep_id);
create sequence ovdep_seq;
create or replace trigger ovdep_trigger_idgen 
    before insert on override_dependencies 
    for each row
    when (new.ovdep_id is null) begin
        select ovdep_seq.nextval
        into :new.ovdep_id
        from dual;
    end;
/
alter table override_dependencies add constraint ovdep_from_methods_fk 
    foreign key (from_method_id) references methods(method_id) on delete cascade enable;
alter table override_dependencies add constraint ovdep_to_methods_fk 
    foreign key (to_method_id) references methods(method_id) on delete cascade enable;    
create index ovdep_from_method_id_I on override_dependencies (from_method_id); 
create index ovdep_to_method_id_I on override_dependencies (to_method_id); 
















