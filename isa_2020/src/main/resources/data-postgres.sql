insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(2, null, 'marica@gmail.com', null, 'Marica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', null, null, null, true)
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, null, 'Higija', 5, null)
insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(4, null, 'mara@gmail.com', null, 'Miroslava', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', null, null, null, true)
insert into "public"."farmaceut" ("id", "apoteka_id") values(4, 1)

insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, null, 'Neka', 5, null)
insert into "public"."apoteka" ("drzava", "grad", "naziv", "ocena", "ulica") values(null, null, 'Nova', 5, null)

insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(1, 1, 'bromazepam', '123', 1)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(2, 1, 'bromazepam', '123', 2)
insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(3, 1, 'fervex', '12345', 2)

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica", "enabled") values(3, 'nedodjija', 'ivica@gmail.com', 'neki', 'Ivica', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'ivic', '772-885', 'slepa', true)

insert into "public"."pacijent" ("id") values(3)



insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(1, '2020-10-09', 1, 3)

insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(2, '2020-10-09', 3, 3)


insert into "public"."authority" ("name") values ('ROLE_PACIJENT')
insert into "public"."authority" ("name") values ('ROLE_DERMATOLOG')
insert into "public"."authority" ("name") values ('ROLE_FARMACEUT')

insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (3, 1)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (2, 2)
insert into "public"."korisnik_authority"  ("korisnik_id", "authority_id") values (4, 3)
