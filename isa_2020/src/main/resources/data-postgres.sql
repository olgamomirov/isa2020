insert into "public"."korisnik" ("id", "drzava", "email", "grad", "ime", "lozinka", "prezime", "telefon", "ulica") values(2, null, null, null, 'Marica', null, null, null, null)
insert into "public"."dermatolog" ("id") values(2)
insert into "public"."apoteka" ("id", "drzava", "grad", "naziv", "ocena", "ulica") values(1, null, null, 'Higija', 5, null)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(1, 2)

insert into "public"."apoteka" ("id", "drzava", "grad", "naziv", "ocena", "ulica") values(2, null, null, 'Neka', 5, null)
insert into "public"."zaposleni_dermatolozi" ("apoteka_id", "dermatolog_id") values(2, 2)
