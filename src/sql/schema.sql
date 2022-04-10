CREATE TABLE Hotel
(
    hotelID                 int,
    nameOrLocation          varchar(50),
    numberOfStars           int CHECK (numberOfStars IN (0, 5)),
    image                   varchar(100),
    description             varchar(1000),
    startingRoomPrice       double,
    distanceFromDowntown    double,
    distanceFromSupermarket double,
    restaurant              BOOLEAN CHECK (restaurant IN (0, 1)),
    breakfastIncluded       BOOLEAN CHECK (breakfastIncluded IN (0, 1)),
    bar                     BOOLEAN CHECK (bar IN (0, 1)),
    freeWifi                BOOLEAN CHECK (freeWifi IN (0, 1)),
    primary key (hotelID)
);

insert into Hotel
values (1, 'Test1', 5, '/images/hotel1.jpg', 'desc', 1, 1, 1, 1, 1, 1, 1);
insert into Hotel
values (2, 'Test2', 5, '/images/hotel1.jpg', 'desc', 1, 1, 1, 1, 1, 1, 1);
insert into Hotel
values (3, 'Test3', 5, '/images/hotel1.jpg', 'desc', 1, 1, 1, 1, 1, 1, 1);

CREATE TABLE Room
(
    hotelID         int,
    roomID          int,
    nrBeds          int,
    pricePerNight   int,
    tv              int,
    privateBathroom int,
    cityView        int,
    primary key (hotelID, roomID),
    foreign key (hotelID) references Hotel (hotelID)
);

insert into Room
values (1, 1, 4, 1, 1, 1, 1);
insert into Room
values (1, 2, 4, 1, 1, 1, 1);
insert into Room
values (2, 1, 8, 1, 1, 1, 1);
insert into Room
values (2, 2, 8, 1, 1, 1, 1);
insert into Room
values (3, 1, 16, 1, 1, 1, 1);
insert into Room
values (3, 2, 16, 1, 1, 1, 1);

CREATE TABLE Booking
(
    hotelID          int,
    roomID           int,
    bookingID        int,
    bookingProcessID int,       /* bookings that result from the same booking process have equal bookingProcessIDs */
    guestID          int UNIQUE,
    guestEmail       varchar(50) UNIQUE,
    guestName        varchar(50),
    checkInDate      text,
    checkOutDate     text,
    primary key (bookingID, hotelID),
    foreign key (roomID, hotelID) references Room (roomID, hotelID)
);

insert into Booking
values (1, 1, 1, 1, 999, 'a', 'test', date('2022-04-01'), date('2022-04-28'));
insert into Booking
values (1, 2, 5, 1, 998, 'b', 'test', date('2022-04-01'), date('2022-04-28'));
insert into Booking
values (2, 2, 2, 1, 997, 'c', 'test', date('2022-05-01'), date('2022-05-31'));
insert into Booking
values (2, 1, 3, 2, 996, 'd', 'test', date('2022-04-16'), date('2022-04-28'));
insert into Booking
values (3, 2, 4, 3, 995, 'e', 'test', date('2022-06-01'), date('2022-06-31'));


/* SQL DROP TABLE query
DROP TABLE Hotel;
DROP TABLE Room;
DROP TABLE Booking;
 */

/* SQL search query with example parameters

select * from Hotel where Hotel.nameOrLocation like 'Test%' and exists(
    select * from Room where Room.hotelID = Hotel.hotelID and not exists(
        select * from Booking where Booking.hotelID = Hotel.hotelID and
            Booking.roomID = Room.roomID and (Booking.checkInDate between date('2022-04-16') and date('2022-04-17') or
            Booking.checkOutDate between date('2022-04-16') and date('2022-04-17') or
            Booking.checkInDate < date('2022-04-16') and Booking.checkOutDate > date('2022-04-17'))));
 */
