CREATE TABLE EVENT_ENTITY (
                             event_id INT AUTO_INCREMENT PRIMARY KEY,
                             event_date DATE NOT NULL,
                             event_type VARCHAR(255) NOT NULL,
                             verplichtingsnummer VARCHAR(255) NOT NULL,
                             betalingskenmerk VARCHAR(255),
                             verval_datum DATE,
                             omschrijving VARCHAR(255),
                             betaalwijze VARCHAR(255),
                             bedrag DECIMAL(10, 2)
);

