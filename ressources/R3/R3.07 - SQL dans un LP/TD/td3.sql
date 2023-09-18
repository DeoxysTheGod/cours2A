---------------------
-- QUESTION 1
---------------------


CREATE OR REPLACE TRIGGER def_controle
BEFORE INSERT OR UDAPTE OF TypeC, DateDebut, DateFin, NoteMax ON CONTROLE
FOR EACH ROW
BEGIN
    IF :NEW.DateFin is NULL OR :NEW.TypeC IN ('TESTS', 'TESTS RATTRAPAGE', 'INTERROGATIONS') 
        THEN :NEW.DateFin := :NEW.DateDeb; 
    END IF;
    IF :NEW.NoteMax IS NULL
        THEN :NEW.NoteMax := 20;
    END IF;
END;

--------------------
-- QUESTION 2
--------------------

CREATE OR REPLACE TRIGGER calc_note
BEFORE INSERT OR UPDATE of Note ON NOTATION
FOR EACH ROW
DECLARE
    myNoteMax NOTATION.Note%TYPE;
BEGIN
    SELECT NoteMax INTO myNoteMax FROM CONTROLE WHERE Idc = :NEW.Idc
    :NEW.NoteDef := :NEW.Note * 20 / myNoteMax;
    
END;


----------------------------------
-- EXERCICE 2
----------------------------------
------------------
-- QUESTION 1
------------------

CREATE OR REPLACE TRIGGER verif_nb_emprunt
BEFORE INSERT ON EMPRUNT
FOR EACH ROW
DECLARE
    nb_emprunt ABONNE.NbEmprunt%TYPE;
BEGIN
    SELECT NbEmprunt FROM ABONNE
    WHERE Ida = :NEW.Ida
    
    IF nb_emprunt >= 5
        THEN RAISE_APPLICATION_ERROR(-2000, 'Trop d''emprunts en cours');
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
    RAISE_APPLICATION_ERROR(-2000, 'Pas d''abonne à ce numéro')
END;

------------------
-- QUESTION 2
------------------

CREATE OR REPLACE TRIGGER maj_emprunt_date_ret
AFTER INSERT OR UPDATE OF DateRet ON EMPRUNT
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE ABONNE SET NbEmprunt = NbEmprunt + 1
        WHERE Ida = :NEW.Ida;
    ELSE
        UPDATE ABONNE SET NbEmprunt = NbEmprunt - 1;
        WHERE Ida = :NEW.Ida;
    END IF;
END;

----------------------
-- QUESTION 3
----------------------

CREATE OR REPLACE TRIGGER contact_abo_prio
AFTER UPDATE OF DateRet ON EMPRUNT
FOR EACH ROW
DECLARE
    id_livre LIVRE.Idl%TYPE;
    abo ABONNE.Ida%TYPE;
BEGIN
    SELECT Idl INTO id_livre FROM EXMEPLAIRE WHERE CodBar = :NEW.CodBar;
    SELECT Ida INTO abo FROM (SELECT MAX(DateR) FROM RESERV WHERE Idl = id_livre);
    
    Avertir(abo.Nom, abo.Prenom, abo.Adresse, (SELECT Titre FROM LIVRE WHERE Idl = id_livre));
    
    UPDATE RESERV
    SET Contact = 'OUI'
    WHERE Ida = abo
END;













