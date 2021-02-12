--lozinke su 123 za sve korisnike i za novokreirane dermatologe, farmaceute, dobavljace i administratore sistema i apoteke

--predefinisani administrator sistema
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values('Srbija', 'dragana@gmail.com', 'Novi Becej', 'Dragana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Momirov', '882559', 'Glavna', true, 'registrovan')

--dermatolog u apotekama 'Jankovic' i 'Demetra'
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'marica@gmail.com', 'Novi Sad', 'Marica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Maric', '555222', 'Slobodana Bajica 2', true, 'registrovan')
insert into "public"."dermatolog" ("id") values(2)

--pacijent
insert into "public"."korisnik" ("drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'ivica@gmail.com', 'Sremska Mitrovica', 'Ivica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ivic', '772885', 'Glavna', true, 'ulogovan')

--farmaceut zaposlen u apoteci 'Jankovic' (status 'ulogovan', dakle ne prijavljuje se prvi put i ne mora da menja lozinku)
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'mara@gmail.com', 'Kragujevac', 'Miroslava', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mirovic', '123123', 'Mise Misica', true, 'ulogovan')

--pacijent
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'aca@gmail.com', 'Subotica', 'Aca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Stevic', '772555', 'Rumska', true, 'ulogovan')

--administrator apoteke 'Jankovic'
insert into "public"."korisnik" ("drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'viki@gmail.com', 'Voganj', 'Violeta', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marceta', '555222', 'Proke Djilasa 1', true, 'registrovan')

--dermatolog u apotekama 'Higija' i 'Jankovic'
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'jovanka@gmail.com', 'Novi Sad', 'Jovanka', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jovic', '555222', 'Bulevar Oslobodjenja 135', true, 'registrovan')
insert into "public"."dermatolog" ("id") values(7)

--dobavljac
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values('Srbija', 'miki@gmail.com', 'Nis', 'Miroslav', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Bulic', '772559', 'Niska', true, 'registrovan')

--dobavljac
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'goca@gmail.com', 'Kikinda', 'Gordana', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Simic', '772559', 'Glavna', true, 'registrovan')

--administrator apoteke 'Jankovic'
insert into "public"."korisnik" ( "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled", "status") values( 'Srbija', 'olga@gmail.com', 'Novi Becej', 'Olga', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Momirov', '882559', 'Pijacna 3', true, 'registrovan')


insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values('Srbija', 'Novi Becej', 'Higija', 2, 'Pijacna 3', 45.5983021, 20.1273597)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values('Srbija', 'Ruma', 'Jankovic', 5, 'Glavna 22', 45.006236, 19.831878)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica", "lat", "lng") values('Srbija', 'Beograd', 'Demetra', 4, 'Veljka Dugosevica 10', 44.773457, 20.489661)

insert into "public"."administrator_apoteke" ("id", "apoteka_id") values(6, 2)
insert into "public"."farmaceut" ("id", "apoteka_id") values(4, 2)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(3, 2)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(1, 7)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 7)

insert into "public"."administrator_apoteke" ("id", "apoteka_id") values(10, 2)

insert into "public"."administrator_sistema" ("id") values(1)

--sifra leka je id u bazi
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'nema dodatnih napomena', 'bromazepam', 'tableta', 'Hemofarm', 'true', 'bromazepam', 'humani lekovi', 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'nema dodatnih napomena', 'fervex', 'prasak', 'Bristol-Myers', 'false', 'paracetamol', 'humani lekovi', 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'nema dodatnih napomena', 'panadol', 'tableta', 'Europharm', 'false', 'paracetamol', 'humani lekovi', 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'nema dodatnih napomena', 'febricet', 'tableta', 'Hemofarm', 'false', 'paracetamol', 'humani lekovi', 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'nema dodatnih napomena', 'brufen', 'tableta', 'Galenika', 'false', 'ibuprofen', 'humani lekovi', 1)
insert into "public"."sifrarnik_lekova" ( "dodatne_napomene", "naziv", "oblik_leka", "proizvodjac", "recept", "sastav", "vrsta_leka", "poeni") values( 'u pocetku tretman moze da izazove crvenilo i peckanje na kozi', 'krema', 'acne intensive', 'Galenika', 'false', 'glicolic, glycerol', 'humani lekovi', 1)


insert into "public"."dobavljac" ("id") values(8)
insert into "public"."dobavljac" ("id") values(9)


insert into "public"."loyalty_program" ( "kategorija_korisnika", "popust", "prag_poena") values( 'regular', 0.0, 0)
insert into "public"."loyalty_program" ( "kategorija_korisnika", "popust", "prag_poena") values( 'silver', 5.0, 7)


--pacijent Ivica ima alergiju na bromazepam
insert into "public"."alergije" ("id") values(1)
insert into "public"."alergije_na_lek" ( "alergija_id","sifrarnik_leka_id") values(1,1)
insert into "public"."pacijent" ("id","penal", "alergija_id", "poeni", "loyalty_program_id") values(3,0,1,5,1)

insert into "public"."pacijent" ("id", "penal", "alergija_id", "poeni", "loyalty_program_id") values(5,0, null, 3,1)

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


insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 1500.0, 'dermatolog', 2, 1)
insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 1000.0, 'farmaceut', 2, 1)

insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 1000.0, 'dermatolog', 2, 2)
insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 500.0, 'farmaceut', 2, 2)


insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 1200.0, 'dermatolog', 2, 3)
insert into "public"."tip_pregleda" ( "cena", "tip", "poeni", "apoteka_id") values( 700.0, 'farmaceut', 2, 3)

insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-04-07T08:00:00.000+01:00/2021-04-07T08:30:00.000+01:00', 'rezervisan', null, 2, 2, null, 3, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'kontrola', '2021-01-30T14:20:00.000+01:00/2021-01-30T15:00:00.000+01:00', 'odradjen', 'bromazepam svaki drugi dan', 2, 2, null, 3, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-15T18:55:00.000+01:00/2021-02-15T19:25:00.000+01:00', 'rezervisan', null, 2, 2, null, 5, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-15T19:10:00.000+01:00/2021-02-15T19:40:00.000+01:00', 'rezervisan', null, 2, null, 4, 3, 4)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-09T16:45:00.000+01:00/2021-03-09T17:15:00.000+01:00', 'default', null, 2, 2, null, null, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-16T13:55:00.000+01:00/2021-02-16T14:25:00.000+01:00', 'rezervisan', null, 2, null, 4, 5, 4)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-05-09T10:25:00.000+01:00/2021-05-09T10:55:00.000+01:00', 'rezervisan', null, 3, 2, null, 5, 5)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-14T11:30:00.000+01:00/2021-03-14T12:00:00.000+01:00', 'default', null, 3, 2, null, null, 5)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-04-12T13:00:00.000+01:00/2021-04-12T13:30:00.000+01:00', 'default', null, 1, 7, null, null, 1)
--primer kada se pacijent ne pojavi i pregled nije zapocet, automatski odlazi posle isteka vremena u neodradjene i pacijent dobija penal 
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-02-05T13:30:00.000+01:00/2021-02-05T14:00:00.000+01:00', 'rezervisan', null, 2, null, 4, 3, 4) 
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'psorijaza', '2021-02-03T13:30:00.000+01:00/2021-02-03T14:00:00.000+01:00', 'odradjen', 'krema na svaka 2 dana', 2, 2, null, 5, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( null, '2021-03-03T13:30:00.000+01:00/2021-03-03T14:00:00.000+01:00', 'rezervisan', null, 3, 2, null, 3, 5)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id", "tip_id") values( 'alergija na bromazepam', '2021-01-03T13:30:00.000+01:00/2021-01-03T14:00:00.000+01:00', 'odradjen', 'ne koristiti bromazepam', 2, null, 4, 3, 4)

--brufen je zamena panadolu
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 3, 5)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 5, 3)
--febricet je zamena fervex-u
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 4)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 4, 2)

insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 2, 3)
insert into "public"."zamene_lekova" ( "lek_id", "zamena_id") values( 3, 2)

insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 5, 1, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 10, 2, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 3, 3, 1)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 6, 1, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 1, 2, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 9, 3, 2)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 4, 1, 3)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 5, 2, 3)
insert into "public"."lek" ( "kolicina", "apoteka_id", "sifra_leka_id") values( 7, 3, 3)

insert into "public"."rezervacija" ("datum_preuzimanja","status", "lek_id", "pacijent_id", "apoteka_id") values('2021-02-21T14:00:00.000+01:00','izdavanje', 2, 3, 2)

insert into "public"."rezervacija" ("datum_preuzimanja", "status", "lek_id", "pacijent_id", "apoteka_id") values('2021-01-13T14:00:00.000+01:00','preuzeto', 8, 3, 2)

--radno vreme farmaceuta u apoteci 'Jankovic'
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-13T08:00:00.000+01:00/2021-02-13T15:00:00.000+01:00',2,null,4)
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-14T08:00:00.000+01:00/2021-02-14T15:00:00.000+01:00',2,null,4)
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-15T08:00:00.000+01:00/2021-02-15T15:00:00.000+01:00',2,null,4)
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-16T08:00:00.000+01:00/2021-02-16T15:00:00.000+01:00',2,null,4)
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-17T08:00:00.000+01:00/2021-02-17T15:00:00.000+01:00',2,null,4)
insert into "public"."radno_vreme" ("interval", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-02-18T08:00:00.000+01:00/2021-02-18T15:00:00.000+01:00',2,null,4)

--narudzbenica za panadol (kolicina 25) u apoteci 'Jankovic' koju je izdao administrator apoteke 6 i dve ponude za narudzbenicu 1
insert into "public"."narudzbenica" ("rok_ponude", "status", "administrator_apoteke_id", "apoteka_id") values ('2021-02-10T14:00:00.000+01:00', 'ceka ponude', 6, 2)
insert into "public"."stavka_narudzbenice" ("kolicina", "lek_id", "narudzbenica_id") values (25, 5, 1)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-21T14:00:00.000+01:00', false, 'ceka na odgovor', 20500.00, 1, 8)
insert into "public"."ponuda" ("rok_isporuke", "rok_istekao", "status", "ukupna_cena", "narudzbenica_id", "dobavljac_id") values ('2021-02-15T14:00:00.000+01:00', false, 'ceka na odgovor', 22000.00, 1, 9)

--narudzbenica jos nema ponude pa ce administrator apoteke(koji je definisao narudzbenicu) moci da je brise i menja
insert into "public"."narudzbenica" ("rok_ponude", "status", "administrator_apoteke_id", "apoteka_id") values ('2021-02-25T14:00:00.000+01:00', 'ceka ponude', 6, 2)
insert into "public"."stavka_narudzbenice" ("kolicina", "lek_id", "narudzbenica_id") values (10, 8, 2)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-01-01T20:18:00.000+01:00/2021-01-31T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 1, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (550.00, 1, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 1, 8)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-02-01T20:18:00.000+01:00/2021-02-28T20:18:00.000+01:00', 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (250.00, 2, 2)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (400.00, 2, 5)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 2, 8)


insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-01-01T20:18:00.000+01:00/2021-01-31T20:18:00.000+01:00', 3)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (170.00, 3, 3)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (450.00, 3, 6)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (200.00, 3, 9)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-02-01T20:18:00.000+01:00/2021-02-28T20:18:00.000+01:00', 3)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (150.00, 4, 3)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (400.00, 4, 6)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (500.00, 4, 9)


insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-01-01T20:18:00.000+01:00/2021-01-31T20:18:00.000+01:00', 1)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (200.00, 5, 1)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (500.00, 5, 4)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (300.00, 5, 7)

insert into "public"."cenovnik" ("interval", "apoteka_id") values ('2021-02-01T20:18:00.000+01:00/2021-02-28T20:18:00.000+01:00', 1)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (220.00, 6, 1)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (360.00, 6, 4)
insert into "public"."cenovnik_stavka" ("cena", "cenovnik_id", "lek_id") values (100.00, 6, 7)

insert into "public"."zalba" ("tekst_zalbe", "status", "pacijent_id", "dermatolog_id") values ('kasnio je na pregled','ceka na odgovor', 5, 2)
insert into "public"."zalba" ("tekst_zalbe", "status", "pacijent_id", "farmaceut_id") values ('nije se pojavio na pregledu','ceka na odgovor', 5, 4)

insert into "public"."akcija_promocija" ( "akcija_promocija", "procenat_akcije", "vreme_vazenja", "apoteka_id") values( 'snizenje', 15.0, '2021-02-10T00:00:00.000+01:00/2021-02-20T00:00:00.000+01:00', 1)
insert into "public"."akcija_promocija" ( "akcija_promocija", "procenat_akcije", "vreme_vazenja", "apoteka_id") values( 'snizenje', 15.0, '2021-02-10T00:00:00.000+01:00/2021-02-20T00:00:00.000+01:00', 2)
insert into "public"."akcija_promocija" ( "akcija_promocija", "procenat_akcije", "vreme_vazenja", "apoteka_id") values( 'snizenje', 15.0, '2021-02-10T00:00:00.000+01:00/2021-02-20T00:00:00.000+01:00', 3)

insert into "public"."stavke_akcije_promocije" ("akcija_promocija_id", "lek_id") values(1, 4)
insert into "public"."stavke_akcije_promocije" ("akcija_promocija_id", "lek_id") values( 2, 2)
insert into "public"."stavke_akcije_promocije" ("akcija_promocija_id", "lek_id") values( 2, 5)
insert into "public"."stavke_akcije_promocije" ( "akcija_promocija_id", "lek_id") values( 3, 9)

insert into "public"."zahtev_za_godisnji" ("interval", "razlog_odbijanja", "status", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-04-05T00:00:00.000+01:00/2021-04-20T00:00:00.000+01:00', null, 'neodobren', 2, 2, null)
insert into "public"."zahtev_za_godisnji" ("interval", "razlog_odbijanja", "status", "apoteka_id", "dermatolog_id", "farmaceut_id") values('2021-03-05T00:00:00.000+01:00/2021-03-20T00:00:00.000+01:00', null, 'neodobren', 2, null, 4)
