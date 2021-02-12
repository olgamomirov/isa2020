insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values('Srbija', 'dragana@gmail.com', 'Novi Becej', 'Dragana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Momirov', '882559', 'Glavna', true, 'registrovan')

insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'marica@gmail.com', 'Novi Sad', 'Marica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Maric', '555222', 'Slobodana Bajica 2', true, 'registrovan')
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."korisnik" ("drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'nedodjija', 'ivica@gmail.com', 'neki', 'Ivica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ivic', '772885', 'slepa', true, 'ulogovan')

insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values(null, 'Novi Becej', 'Higija', 2, 'Pijacna 3',45.5983021, 20.1273597)
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( null, 'mara@gmail.com', null, 'Miroslava', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mirovic', null, null, true, 'ulogovan')

insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'aca@gmail.com', 'neki', 'Aca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Stevic', '772555', 'Rumska', true, 'ulogovan')


insert into "public"."korisnik" ("drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'viki@gmail.com', 'Novi Sad', 'Violeta', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marceta', '555222', 'Slobodana Bajica 2', true, 'registrovan')

insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'jovanka@gmail.com', 'Novi Sad', 'Jovanka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jovic', '555222', 'Sklj', true, 'registrovan')
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


--insert into "public"."pacijent" ("id", "penal") values(3,0)
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values('Srbija', 'v@gmail.com', 'Nis', 'Miroslav', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bulic', '772559', 'Niska', true, 'registrovan')
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'vio1995@gmail.com', 'Kragujevac', 'Dragana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Simic', '772559', 'Glavna', true, 'registrovan')
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'olga@gmail.com', 'Novi Becej', 'Olga', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Momirov', '882559', 'Glavna', true, 'registrovan')

insert into "public"."administrator_apoteke" ("id", "apoteka_id") values(10, 2)

insert into "public"."administrator_sistema" ("id") values(1)

insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( null, 'bromazepam', '', null, 'true', null, null, 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( null, 'fervex', null, null, 'false', null, null, 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( null, 'panadol', null, null, 'false', null, null, 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( null, 'febricet', null, null, 'false', null, null, 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( null, 'brufen', null, null, 'false', null, null, 1)



insert into "public"."dobavljac" ("id") values(8)
insert into "public"."dobavljac" ("id") values(9)


insert into "public"."loyalty_program" ( "kategorija_korisnika", "popust", "prag_poena") values( 'regular', 0.0, 0)
insert into "public"."loyalty_program" ( "kategorija_korisnika", "popust", "prag_poena") values( 'silver', 5.0, 7)


insert into "public"."pacijent" ("id", "penal", "alergija_id", "poeni", "loyalty_program_id") values(5,0, null, 3,1)

insert into "public"."alergije" ("id") values(1)
insert into "public"."alergije_na_lek" ( "alergija_id","sifrarnik_leka_id") values(1,1)
insert into "public"."pacijent" ("id","penal", "alergija_id", "poeni", "loyalty_program_id") values(3,0,1,5,1)



insert into "public"."authority" ("name") values ('ROLE_PACIJENT')
insert into "public"."authority" ("name") values ('ROLE_DERMATOLOG')
insert into "public"."authority" ("name") values ('ROLE_FARMACEUT')
insert into "public"."authority" ("name") values ('ROLE_ADMINISTRATOR')
insert into "public"."authority" ("name") values ('ROLE_DOBAVLJAC')
insert into "public"."authority" ("name") values ('ROLE_SISTEM')

insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (3, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (2, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (4, 3)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (5, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (6, 4)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (7, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (8, 5)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (9, 5)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (10, 4)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (1, 6)


insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 1000.0, 'dermatolog', 2, 2)
insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 500.0, 'farmaceut', 2, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'zdravo', '2021-11-07T08:00:00.000+01:00/2021-11-07T08:30:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'kontrola', '2021-01-30T14:20:00.000+01:00/2021-01-30T15:00:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-12T14:15:00.000+01:00/2021-02-12T14:45:00.000+01:00', 'rezervisan', 'nema', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'strasno', '2021-02-17T09:15:00.000+01:00/2021-02-17T09:45:00.000+01:00', 'rezervisan', 'cccc', 2, null, 4, 3, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-09T16:45:00.000+01:00/2021-03-09T17:15:00.000+01:00', 'default', 'null', 2, 2, null, null, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-12T13:55:00.000+01:00/2021-02-12T14:25:00.000+01:00', 'rezervisan', 'null', 2, null, 4, 5, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-12-09T10:25:00.000+01:00/2021-12-09T10:55:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 5, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-14T11:30:00.000+01:00/2021-03-14T12:00:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 3, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-09-12T13:00:00.000+01:00/2021-09-12T13:30:00.000+01:00', 'rezervisan', 'null', 2, 2, null, 5, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-05T13:30:00.000+01:00/2021-02-05T14:00:00.000+01:00', 'rezervisan', 'null', 2, null, 4, 3, 2)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'psorijaza', '2021-02-03T13:30:00.000+01:00/2021-02-03T14:00:00.000+01:00', 'odradjen', 'krema na svaka 2 dana', 2, 2, null, 5, 1)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-03T13:30:00.000+01:00/2021-03-03T14:00:00.000+01:00', 'rezervisan', null, 3, 2, null, 3, 1)


insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 1)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 3)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 4)

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

insert into "public"."rezervacija" ("datum_preuzimanja","status", "lek_id", "pacijent_id", "apoteka_id") values('2021-02-21T14:00:00.000+01:00','izdavanje', 2, 3, 2)

insert into "public"."rezervacija" ("datum_preuzimanja", "status", "lek_id", "pacijent_id", "apoteka_id") values('2021-01-13T14:00:00.000+01:00','preuzeto', 3, 3, 3)

insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-13T08:00:00.000+01:00/2021-09-13T15:00:00.000+01:00',2,null,4)

insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-25T08:00:00.000+01:00/2021-09-25T15:00:00.000+01:00',2,null,4)

insert into "public"."narudzbenica" ("rok_ponude", "status", "administrator_apoteke_id", "apoteka_id") values ('2021-02-02T14:00:00.000+01:00', 'ceka ponude', 6, 2)
insert into "public"."stavka_narudzbenice" ("kolicina", "lek_id", "narudzbenica_id") values (25, 5, 1)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-21T14:00:00.000+01:00', false, 'ceka na odgovor', 20500.00, 1, 8)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-11T14:00:00.000+01:00', false, 'ceka na odgovor', 22000.00, 1, 9)

insert into "public"."narudzbenica" ("rok_ponude", "status", "administrator_apoteke_id", "apoteka_id") values ('2021-02-02T14:00:00.000+01:00', 'ceka ponude', 6, 2)
insert into "public"."stavka_narudzbenice" ("kolicina", "lek_id", "narudzbenica_id") values (10, 8, 2)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-01-01T20:18:00.000+01:00/2021-01-31T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 1, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (550.00, 1, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 1, 8)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-02-01T20:18:00.000+01:00/2021-02-28T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 2, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (400.00, 2, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 2, 8)


insert into "public"."zalba" ("tekst_zalbe", "status", "pacijent_id", "dermatolog_id") values ('kasnio je na pregled','ceka na odgovor', 5, 2)
insert into "public"."zalba" ("tekst_zalbe", "status", "pacijent_id", "farmaceut_id") values ('ruzno ponasanje','ceka na odgovor', 5, 4)
