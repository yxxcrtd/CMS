/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2015/1/26 0:02:35                            */
/*==============================================================*/

drop table "T_News" cascade constraints;
drop sequence "T_News_SEQUENCE";
drop trigger "T_News_Trigger";

drop table "T_Service" cascade constraints;
drop sequence "S_T_Service";
drop trigger "T_T_Service";

drop table "T_Press" cascade constraints;
drop sequence "S_T_Press";
drop trigger "T_T_Press";

drop table "T_Dynamic" cascade constraints;
drop sequence "S_T_Dynamic";
drop trigger "T_T_Dynamic";

drop table "T_Advertisement" cascade constraints;
drop sequence "S_T_Advertisement";
drop trigger "T_T_Advertisement";

drop table "T_Config" cascade constraints;
drop sequence "S_T_Config";
drop trigger "T_T_Config";


/*==============================================================*/
/* Table: "T_News"                                              */
/*==============================================================*/
create table "T_News"  (
   "NewsId"				NUMBER(4)						not null,
   "NewsTitle"			NVARCHAR2(128)					not null,
   "NewsSource"			NVARCHAR2(128),
   "NewsUrl"       		NVARCHAR2(128),
   "NewsContent"		CLOB							not null,
   "NewsAuthor"			NVARCHAR2(16),
   "NewsCreateTime"		TIMESTAMP						not null,
   "NewsOrderby"		SMALLINT						not null,
   constraint PK_T_NEWS primary key ("NewsId")
);
/*==============================================================*/
/* Sequence："T_News_SEQUENCE"                                  */
/*==============================================================*/
create sequence "T_News_SEQUENCE"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_News_Trigger"                                    */
/*==============================================================*/
create or replace trigger "T_News_Trigger" 
	before insert on "T_News" for each row
begin
	--IF (:new."NewsId" IS NULL) THEN
		SELECT "T_News_SEQUENCE".nextval INTO :new."NewsId" FROM DUAL;
	--END IF;
end "T_News_Trigger";


/*==============================================================*/
/* Table: "T_Service"                                           */
/*==============================================================*/
create table "T_Service"  (
   "ServiceId"				NUMBER(4)					not null,
   "ServiceChineseTitle"	NVARCHAR2(64)				not null,
   "ServiceEnglishTitle"	NVARCHAR2(64)				not null,
   "ServiceChineseContent"	CLOB						not null,
   "ServiceEnglishContent"	CLOB						not null,
   "ServiceLink"			NVARCHAR2(128),
   "ServiceOrderby"			SMALLINT					not null,
   constraint PK_T_SERVICE primary key ("ServiceId")
);
/*==============================================================*/
/* Sequence："S_T_Service"     	                                */
/*==============================================================*/
create sequence "S_T_Service"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_T_Service"                                       */
/*==============================================================*/
create or replace trigger "T_T_Service" 
	before insert on "T_Service" for each row
begin
	SELECT "S_T_Service".nextval INTO :new."ServiceId" FROM DUAL;
end "T_T_Service";


/*==============================================================*/
/* Table: "T_Press"                                             */
/*==============================================================*/
create table "T_Press"  (
   "PressId"            NUMBER(4)                        not null,
   "PressLocation"      SMALLINT                        not null,
   "PressTitle"         NVARCHAR2(64)                    not null,
   "PressLink"         NVARCHAR2(256)                   not null,
   "PressContent"		CLOB							not null,
   "PressOrderby"       SMALLINT                        not null,
   "PressLogo"          NVARCHAR2(32)                    not null,
   "PressVisible"		SMALLINT				not null DEFAULT 0,
   constraint PK_T_PRESS primary key ("PressId")
);
/*==============================================================*/
/* Sequence："S_T_Press"     	                                */
/*==============================================================*/
create sequence "S_T_Press"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_T_Press"                                       */
/*==============================================================*/
create or replace trigger "T_T_Press" 
	before insert on "T_Press" for each row
begin
	SELECT "S_T_Press".nextval INTO :new."PressId" FROM DUAL;
end "T_T_Press";


/*==============================================================*/
/* Table: "T_Dynamic"                                           */
/*==============================================================*/
create table "T_Dynamic"  (
   "DynamicId"          NUMBER(4)                       not null,
   "DynamicObj"       	SMALLINT                       not null,
   "DynamicChinese"     CLOB                            not null,
   "DynamicEnglish"     CLOB                            not null,
   constraint PK_T_DYNAMIC primary key ("DynamicId")
);
/*==============================================================*/
/* Sequence："S_T_Dynamic"     	                                */
/*==============================================================*/
create sequence "S_T_Dynamic"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_T_Dynamic"                                       */
/*==============================================================*/
create or replace trigger "T_T_Dynamic" 
	before insert on "T_Dynamic" for each row
begin
	SELECT "S_T_Dynamic".nextval INTO :new."DynamicId" FROM DUAL;
end "T_T_Dynamic";


/*==============================================================*/
/* Table: "T_Advertisement"                                     */
/*==============================================================*/
create table "T_Advertisement"  (
   "AdvertisementId"			NUMBER(4)				not null,
   "AdvertisementType"			SMALLINT				not null,
   "AdvertisementTitle"			NVARCHAR2(32),
   "AdvertisementLink"			NVARCHAR2(128),
   "AdvertisementContent"		CLOB,
   "AdvertisementLocation"		SMALLINT				not null,
   "AdvertisementOrderby"       SMALLINT				not null,
   "AdvertisementPicture"		NVARCHAR2(22),
   "AdvertisementVisible"		SMALLINT				not null DEFAULT 0,
   "AdvertisementAttachment"	NVARCHAR2(128),
   constraint PK_T_ADVERTISEMENT primary key ("AdvertisementId")
);
/*==============================================================*/
/* Sequence："S_T_Advertisement"     	                                */
/*==============================================================*/
create sequence "S_T_Advertisement"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_T_Advertisement"                                       */
/*==============================================================*/
create or replace trigger "T_T_Advertisement" 
	before insert on "T_Advertisement" for each row
begin
	SELECT "S_T_Advertisement".nextval INTO :new."AdvertisementId" FROM DUAL;
end "T_T_Advertisement";



/*==============================================================*/
/* Table: "T_Config"                                     */
/*==============================================================*/
create table "T_Config"  (Visible
   "ConfigId"			NUMBER(4)				not null,
   "ConfigCnTitle"			NVARCHAR2(128),
   "ConfigEnTitle"			NVARCHAR2(128),
   "ConfigUrl"			NVARCHAR2(256),
   "ConfigVisible"			NUMBER(4),
   "ConfigType"		NVARCHAR2(32)
);
/*==============================================================*/
/* Sequence："S_T_Config"     	                                */
/*==============================================================*/
create sequence "S_T_Config"
	increment by 1 
	start with 1 
	nomaxvalue    
	nocycle 
	cache 10;
/*==============================================================*/
/* Trigger: "T_T_Config"                                       */
/*==============================================================*/
create or replace trigger "T_T_Config" 
	before insert on "T_Config" for each row
begin
	SELECT "S_T_Config".nextval INTO :new."ConfigId" FROM DUAL;
end "T_T_Config";

