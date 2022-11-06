-- new column addition
ALTER TABLE corsarineri.indizio
ADD aiuto_arrivato2 boolean;

-- new column for date of aiuto2
ALTER TABLE corsarineri.indizio
ADD orario_aiuto2 timestamp without time zone NOT NULL;




