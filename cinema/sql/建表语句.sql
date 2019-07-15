create table Show
(
    Hall_id   int      not null
        constraint Show_MovieHall_Hall_id_fk
            references MovieHall,
    Movie_id  int      not null
        constraint Show_Movie_Movie_id_fk
            references Movie,
    Show_time datetime not null,
    constraint Show_pk
        primary key nonclustered (Hall_id, Movie_id, Show_time)
)
create table Audience
(
    Aud_id   int          not null
        constraint Audience_pk_2
            primary key nonclustered,
    Name     varchar(20)  not null,
    Password varchar(255) not null,
    Tel      varchar(30),
    Type     varchar(20),
    constraint Audience_pk
        unique (Aud_id, Name)
)
create table Arrange
(
    Aud_id       int      not null
        constraint Arrange_Audience_Aud_id_fk
            references Audience,
    Hall_id      int      not null
        constraint Arrange_MovieHall_Hall_id_fk
            references MovieHall,
    Movie_id     int      not null
        constraint Arrange_Movie_Movie_id_fk
            references Movie,
    Line         int      not null,
    Row          int      not null,
    Arrange_time datetime not null,
    constraint Arrange_pk
        primary key (Aud_id, Hall_id, Movie_id, Line, Row, Arrange_time)
)
create table Movie
(
    Movie_id   int         not null
        constraint Movie_pk_2
            primary key nonclustered
        constraint Movie_pk
            unique,
    Movie_name varchar(20) not null,
    Base_price float       not null,
    Category   varchar(255),
    Loc        varchar(10),
    Director   varchar(10),
    Start_time datetime,
    End_time   datetime
)
create table MovieHall
(
    Hall_id   int not null
        constraint MovieHall_pk
            primary key nonclustered,
    Type      varchar(30),
    Row_sum   int,
    Line_sum  int,
    Add_price int
)