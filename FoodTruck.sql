CREATE TABLE item( iid INT NOT NULL AUTO_INCREMENT, 
name VARCHAR(40), 
description VARCHAR(200), 
qty INT, 
UNIQUE(name), 
PRIMARY KEY(iid) 
) ENGINE = MyISAM;

CREATE TABLE location( 
    lid INT(5) NOT NULL AUTO_INCREMENT , 
    name VARCHAR(40) NOT NULL UNIQUE, 
    arrival VARCHAR(40) NOT NULL,
    PRIMARY KEY (lid) 
) ENGINE = MyISAM;

CREATE TABLE orders( 
  oid INT NOT NULL AUTO_INCREMENT,
  status INT NOT NULL DEFAULT 0, # 0 = open order, 1 = ready order, 2 = completed order  
  lid INT NOT NULL, #or varchar(40) if location table is not used 
  cname VARCHAR(20), 
  odate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , 
  PRIMARY KEY (oid),
  FOREIGN KEY (lid) REFERENCES location(lid) ON DELETE CASCADE
  ) ENGINE = MyISAM;
  
  CREATE TABLE order_items( 
  oid INT NOT NULL, 
  iid INT NOT NULL , 
  qty int NOT NULL DEFAULT 1 , 
  cost double not null, 
  FOREIGN KEY (iid) REFERENCES item(iid) ON DELETE CASCADE,
  FOREIGN KEY (oid) REFERENCES orders(oid) ON DELETE CASCADE
  ) ENGINE = MyISAM;