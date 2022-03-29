CREATE TABLE Hotel
(
    hotelID                     int,
    name                        varchar(50),
    numberOfStars               int,
    image                       varchar(50),
    description                 varchar(1000),
    roomPrice                   double,
    distanceFromDowntown        double,
    distanceFromSupermarket     double,
    restaurant                  int,
    bar                         int,
    freeWifi                    int,
    facilitiesForDisabledGuests int,
    primary key (hotelID)
);

CREATE TABLE Room
(
    roomID          int,
    hotelID         int,
    nrBeds          int,
    pricePerNight   int,
    tv              int,
    privateBathroom int,
    cityView        int,
    primary key (roomID, hotelID),
    foreign key (hotelID) references Hotel (hotelID)
);

CREATE TABLE Booking
(
    bookingProcessID int, /* bookings that result from the same booking process have equal bookingProcessIDs */
    bookingID        int,
    roomID           int,
    hotelID          int,
    guestID          int,
    guestName        varchar(50),
    checkInDate      date,
    checkOutDate     date,
    nrBeds           int,
    primary key (bookingID, hotelID),
    foreign key (roomID, hotelID) references Room (roomID, hotelID)
);
