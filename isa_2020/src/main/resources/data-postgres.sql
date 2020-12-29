insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(2, 'Srbija', 'marica@gmail.com', 'Novi Sad', 'Marica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Maric', '555-222', 'Slobodana Bajica 2', true)
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, 'Novi Becej', 'Higija', 2, null)
insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(4, null, 'mara@gmail.com', null, 'Miroslava', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', null, null, null, true)
insert into "public"."farmaceut" ("id", "apoteka_id") values(4, 1)

insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, 'Ruma', 'Neka', 5, null)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, 'Beograd', 'Nova', 4, null)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(3, 2)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(1, 1, 'bromazepam', '123', 1)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(2, 1, 'bromazepam', '123', 2)
insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(3, 1, 'fervex', '12345', 2)

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(3, 'nedodjija', 'ivica@gmail.com', 'neki', 'Ivica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ivic', '772-885', 'slepa', true)

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(5, 'Srbija', 'aca@gmail.com', 'neki', 'Aca', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Stevic', '772-555', 'Rumska', true)

insert into "public"."pacijent" ("id") values(3)

insert into "public"."pacijent" ("id") values(5)



insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(1, '2020-10-09', 1, 3)

insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(2, '2020-10-09', 3, 3)


insert into "public"."authority" ("name") values ('ROLE_PACIJENT')
insert into "public"."authority" ("name") values ('ROLE_DERMATOLOG')
insert into "public"."authority" ("name") values ('ROLE_FARMACEUT')

insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (3, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (2, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (4, 3)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (5, 1)


insert into "public"."tip_pregleda" ( "cena", "tip") values( 1000.0, 'pregled mladeza')
insert into "public"."tip_pregleda" ( "cena", "tip") values( 500.0, 'kontrola')insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id") values( 'zdravo', '', 'odradjen', 'nema', 2, 2, null, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id") values( 'kontrola', '', 'odradjen', 'nema', 2, 2, null, 3)
insert into "public"."pregled" ( "dijagnoza", "interval", "status", "terapija", "apoteka_id", "dermatolog_id", "farmaceut_id", "pacijent_id") values( 'strasno', '', 'odradjen', 'cccc', 2, null, 4, 3)
insert into "public"."tipovi_pregledi" ("pregled_id", "tip_pregleda_id") values(1, 1)
insert into "public"."tipovi_pregledi" ("pregled_id", "tip_pregleda_id") values(2, 2)