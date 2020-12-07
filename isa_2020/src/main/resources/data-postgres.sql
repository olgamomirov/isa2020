insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica") values(2, null, null, null, 'Marica', null, null, null, null)
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."apoteka" ("id", "drzava", "grad", "naziv", "ocena", "ulica") values(1, null, null, 'Higija', 5, null)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(1, 2)

insert into "public"."apoteka" ("id", "drzava", "grad", "naziv", "ocena", "ulica") values(2, null, null, 'Neka', 5, null)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(1, 1, 'bromazepam', '123', 1)

insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(2, 1, 'bromazepam', '123', 2)
insert into "public"."lek" ("id", "kolicina", "naziv", "sifra", "apoteka_id") values(3, 1, 'fervex', '12345', 2)

insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica") values(3, null, null, null, 'Ivica', null, null, null, null)

insert into "public"."pacijent" ("id") values(3)

insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(1, '2020-10-09', 1, 3)

insert into "public"."rezervacija" ("id", "datum_preuzimanja", "lek_id", "pacijent_id") values(2, '2020-10-09', 3, 3)