drop table if exists phone2color;
drop table if exists colors;
drop table if exists stocks;
drop table if exists phones;
drop table if exists orders;
drop table if exists order_items;

create table colors
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50),
    UNIQUE (code)
);

create table phones
(
    id                    BIGINT AUTO_INCREMENT primary key,
    brand                 VARCHAR(50)  NOT NULL,
    model                 VARCHAR(254) NOT NULL,
    price                 FLOAT,
    displaySizeInches     FLOAT,
    weightGr              SMALLINT,
    lengthMm              FLOAT,
    widthMm               FLOAT,
    heightMm              FLOAT,
    announced             DATETIME,
    deviceType            VARCHAR(50),
    os                    VARCHAR(100),
    displayResolution     VARCHAR(50),
    pixelDensity          SMALLINT,
    displayTechnology     VARCHAR(50),
    backCameraMegapixels  FLOAT,
    frontCameraMegapixels FLOAT,
    ramGb                 FLOAT,
    internalStorageGb     FLOAT,
    batteryCapacityMah    SMALLINT,
    talkTimeHours         FLOAT,
    standByTimeHours      FLOAT,
    bluetooth             VARCHAR(50),
    positioning           VARCHAR(100),
    imageUrl              VARCHAR(254),
    description           VARCHAR(4096),
    CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color
(
    phoneId BIGINT,
    colorId BIGINT,
    CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks
(
    phoneId  BIGINT   NOT NULL,
    stock    SMALLINT NOT NULL,
    reserved SMALLINT NOT NULL,
    UNIQUE (phoneId),
    CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table orders
(
    id              BIGINT auto_increment primary key,
    subtotal        DOUBLE      NOT NULL,
    deliveryPrice   DOUBLE      NOT NULL,
    firstName       VARCHAR(30) NOT NULL,
    lastName        VARCHAR(30) NOT NULL,
    deliveryAddress VARCHAR(50) NOT NULL,
    contactPhoneNo  VARCHAR(50) NOT NULL,
    additionalInfo  VARCHAR(512),
    orderStatus     VARCHAR(20) not null,
    date            TIMESTAMP   not null
);

create table order_items
(
    orderId  BIGINT  not null,
    phoneId  BIGINT  not null,
    id       BIGINT AUTO_INCREMENT primary key,
    quantity INTEGER not null
);