drop table users;

CREATE TABLE users (
                       user_id bigint NOT NULL AUTO_INCREMENT,
                       user_login varchar(16) NOT NULL UNIQUE,
                       user_password varchar(16) NOT NULL,
                       user_email varchar(16) NOT NULL,
                    --   user_name varchar(16) NOT NULL,
                    --  user_surname varchar(16) NOT NULL,
                       PRIMARY KEY (user_id)
);

-- INSERT INTO users VALUES (1,'anet','4637','sgws@egre.com');
drop table cards;

CREATE  TABLE cards (card_id bigint NOT NULL AUTO_INCREMENT,
                     user_id bigint NOT NULL UNIQUE,
                     card_sum bigint NOT NULL,
                     bill_id bigint NOT NULL,
                     card_status boolean,
                     PRIMARY KEY (card_id)
);
drop table bill;
CREATE  TABLE bill (bill_id bigint NOT NULL AUTO_INCREMENT,
                    bill_sum bigint NOT NULL,
                    bill_name varchar(16) NOT NULL,
                    bill_status boolean NOT NULL,

                    PRIMARY KEY (bill_id)
);

drop table Transaction_history;
CREATE  TABLE Transaction_history(tr_id bigint NOT NULL AUTO_INCREMENT,
                                  tr_date datetime,
                                  tr_status varchar(16) NOT NULL,
                                  card_id bigint NOT NULL,
                                  PRIMARY KEY(tr_id)
);