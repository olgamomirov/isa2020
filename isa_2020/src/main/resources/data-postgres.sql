insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(2, 'Srbija', 'marica@gmail.com', 'Novi Sad', 'Marica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Maric', '555222', 'Slobodana Bajica 2', true, 'registrovan')
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values(null, 'Novi Becej', 'Higija', 2, 'Pijacna 3',45.598344, 20.127563)
insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(4, null, 'mara@gmail.com', null, 'Miroslava', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mirovic', null, null, true, 'ulogovan')


insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(6, 'Srbija', 'viki@gmail.com', 'Novi Sad', 'Violeta', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marceta', '555222', 'Slobodana Bajica 2', true, 'registrovan')

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(7, 'Srbija', 'jovanka@gmail.com', 'Novi Sad', 'Jovanka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jovic', '555222', 'Sklj', true, 'registrovan')
insert into "public"."dermatolog" ("id") values(7)

  
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values(null, 'Ruma', 'Neka', 5, null,45.006236, 19.831878)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values(null, 'Beograd', 'Nova', 4, null,44.773457, 20.489661)
insert into "public"."administrator_apoteke" ("id", "apoteka_id") values(6, 2)
insert into "public"."farmaceut" ("id", "apoteka_id") values(4, 2)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(3, 2)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(1, 7)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 7)


--insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(1, 2, 'bromazepam', '123', 1)

--insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(2, 1, 'bromazepam', '123', 2)
--insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(3, 1, 'fervex', '12345', 2)

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(3, 'nedodjija', 'ivica@gmail.com', 'neki', 'Ivica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ivic', '772885', 'slepa', true, 'registrovan')

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(5, 'Srbija', 'aca@gmail.com', 'neki', 'Aca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Stevic', '772555', 'Rumska', true, 'registrovan')

--insert into "public"."pacijent" ("id", "penal") values(3,0)
insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(8, 'Srbija', 'v@gmail.com', 'Nis', 'Miroslav', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bulic', '772559', 'Niska', true, 'registrovan')
insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values(9, 'Srbija', 'violetamarceta1995@gmail.com', 'Kragujevac', 'Dragana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Simic', '772559', 'Glavna', true, 'registrovan')



insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka") values( null, 'bromazepam', '', null, 'true', null, null)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka") values( null, 'fervex', null, null, 'false', null, null)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka") values( null, 'panadol', null, null, 'false', null, null)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka") values( null, 'febricet', null, null, 'false', null, null)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka") values( null, 'brufen', null, null, 'false', null, null)



insert into "public"."dobavljac" ("id") values(8)
insert into "public"."dobavljac" ("id") values(9)

insert into "public"."pacijent" ("id", "penal") values(5,0)

insert into "public"."alergije" ("id") values(1)
insert into "public"."alergije_na_lek" ( "alergija_id","sifrarnik_leka_id") values(1,1)
insert into "public"."pacijent" ("id","penal", "alergija_id") values(3,0,1)



insert into "public"."authority" ("name") values ('ROLE_PACIJENT')
insert into "public"."authority" ("name") values ('ROLE_DERMATOLOG')
insert into "public"."authority" ("name") values ('ROLE_FARMACEUT')
insert into "public"."authority" ("name") values ('ROLE_ADMINISTRATOR')
insert into "public"."authority" ("name") values ('ROLE_DOBAVLJAC')

insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (3, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (2, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (4, 3)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (5, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (6, 4)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (7, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (8, 5)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (9, 5)

insert into "public"."tip_pregleda" ( "cena", "tip") values( 1000.0, 'pregled mladeza')
insert into "public"."tip_pregleda" ( "cena", "tip") values( 500.0, 'kontrola')
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'zdravo', '2021-11-07T08:00:00.000+01:00/2021-11-07T15:00:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'kontrola', '2021-01-30T23:28:00.000+01:00/2021-01-30T23:55:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-01-30T23:28:00.000+01:00/2021-01-30T23:55:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'strasno', '2021-01-17T08:00:00.000+01:00/2021-01-17T15:00:00.000+01:00', 'rezervisan', 'cccc', 2, null, 4, 3, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-23T08:00:00.000+01:00/2021-02-23T15:00:00.000+01:00', 'default', 'null', 2, 2, null, null, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-28T08:00:00.000+01:00/2021-03-28T15:00:00.000+01:00', 'rezervisan', 'null', 2, null, 4, 5, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-12-09T08:00:00.000+01:00/2021-12-09T15:00:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 5, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-14T08:00:00.000+01:00/2021-03-14T15:00:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-09-12T08:00:00.000+01:00/2021-09-12T15:00:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 5, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-05T08:00:00.000+01:00/2021-02-05T15:00:00.000+01:00', 'rezervisan', 'null', 2, null, 4, 3, 2)
--insert into "public"."tipovi_pregledi" ("pregled_id", "tip_pregleda_id") values(1, 1)
--insert into "public"."tipovi_pregledi" ("pregled_id", "tip_pregleda_id") values(2, 2)
--obrisi tipovi pregledi jer smo ih izbacili iz modela


insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 1)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 3)

insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 5, 1, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 10, 2, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 3, 3, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 6, 1, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 1, 2, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 9, 3, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 4, 1, 3)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 5, 2, 3)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 7, 3, 3)
--insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 5, 2, 4)

insert into "public"."rezervacija" ("datum_preuzimanja","status", "lek_id", "pacijent_id", "apoteka_id") values('2021-01-31T14:00:00.000+01:00','izdavanje', 2, 3, 2)

insert into "public"."rezervacija" ("datum_preuzimanja", "status", "lek_id", "pacijent_id", "apoteka_id") values('2021-01-13T14:00:00.000+01:00','preuzeto', 3, 3, 3)

insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-01-13T08:00:00.000+01:00/2021-09-13T15:00:00.000+01:00',2,null,4)

insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-01-25T08:00:00.000+01:00/2021-09-25T15:00:00.000+01:00',2,null,4)

insert into "public"."narudzbenica" ("rok_ponude", "status", "apoteka_id") values ('2021-02-02T14:00:00.000+01:00', 'ceka ponude', 2)
insert into "public"."stavka_narudzbenice" ("kolicina", "lek_id", "narudzbenica_id") values (25, 5, 1)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-21T14:00:00.000+01:00', false, 'ceka na odgovor', 20500.00, 1, 8)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-11T14:00:00.000+01:00', false, 'ceka na odgovor', 22000.00, 1, 9)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-01-01T20:18:00.000+01:00/2021-01-31T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 1, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (550.00, 1, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 1, 8)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-02-01T20:18:00.000+01:00/2021-02-28T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 2, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (400.00, 2, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 2, 8)


