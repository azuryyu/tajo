CREATE TABLE COLUMNS (
  TID INT NOT NULL,
  COLUMN_NAME VARCHAR(255) BINARY NOT NULL,
  ORDINAL_POSITION INT NOT NULL,
  DATA_TYPE CHAR(16),
  TYPE_LENGTH INTEGER,
  PRIMARY KEY (TID, COLUMN_NAME),
  FOREIGN KEY (TID) REFERENCES TABLES (TID) ON DELETE CASCADE
)
