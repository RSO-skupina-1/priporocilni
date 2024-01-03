-- Positive comments with high ratings
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (7, 2, 'Priljubljeno mesto za potovanje, toplo priporočam!', 5, TIMESTAMP '2021-11-12 10:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (8, 3, 'Super destinacija, vredna obiska!', 5, TIMESTAMP '2022-04-25 16:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (9, 4, 'Najboljše doživetje doslej, 5/5!', 5, TIMESTAMP '2023-01-05 08:00:00');

-- Mixed reviews with moderate ratings
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (10, 5, 'V redu kraj, nekaj prostora za izboljšave.', 3, TIMESTAMP '2022-08-17 14:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (11, 1, 'Prijeten kraj, vendar precej gneča.', 3, TIMESTAMP '2023-04-30 12:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (12, 2, 'Ni slabo, ampak tudi ni vrhunsko.', 2, TIMESTAMP '2021-10-08 19:20:00');

-- Negative comments with low ratings
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (13, 3, 'Slaba izkušnja, ne bi priporočal.', 1, TIMESTAMP '2022-12-18 17:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (14, 4, 'Razočaranje, ne vredno časa.', 1, TIMESTAMP '2023-03-22 11:10:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (15, 5, 'Najslabša destinacija doslej, popolnoma razočaran.', 1, TIMESTAMP '2021-07-14 23:45:00');

-- More varied comments with different ratings
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (16, 1, 'Krasen kraj za sprostitev, priporočam!', 4, TIMESTAMP '2022-05-19 13:55:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (17, 2, 'Noro lepo, ampak precej drago.', 3, TIMESTAMP '2023-05-07 20:40:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (18, 3, 'Zanimiva destinacija, vendar premalo aktivnosti.', 2, TIMESTAMP '2021-09-28 09:25:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (19, 4, 'Okusna hrana, a pomanjkanje kulturnih znamenitosti.', 3, TIMESTAMP '2023-08-02 18:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (20, 5, 'Povprečno, nič posebnega.', 2, TIMESTAMP '2022-02-14 21:50:00');

-- User ID 1
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 3, 'Še en čudovit obisk, nikoli se ne naveličam tega kraja!', 5, TIMESTAMP '2023-12-01 14:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 4, 'Odlična postrežba in udobne nastanitve, toplo priporočam!', 5, TIMESTAMP '2022-07-10 21:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 2, 'Vedno znova navdušen nad lepoto tega kraja.', 4, TIMESTAMP '2023-03-18 11:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 5, 'Domačnost in mir, to je tisto, kar iščem.', 4, TIMESTAMP '2022-10-05 08:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 1, 'Očarljiva destinacija, vsakič znova navdušena.', 5, TIMESTAMP '2023-05-22 16:20:00');

-- User ID 2
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 4, 'Odličen kraj za družinske počitnice, otroci so uživali!', 4, TIMESTAMP '2022-12-22 09:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 5, 'Mirno in sproščujoče okolje, idealno za umik od vsakdanjega vrveža.', 5, TIMESTAMP '2023-09-08 13:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 1, 'Kranjska Gora vedno ponudi nekaj novega, veselim se vsakega obiska.', 4, TIMESTAMP '2022-06-17 18:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 2, 'Prijetna destinacija za vse ljubitelje narave.', 3, TIMESTAMP '2023-02-12 22:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 3, 'Lepa kulisa in prijazno osebje, a nekoliko draga.', 3, TIMESTAMP '2021-08-30 10:30:00');

-- User ID 3
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (3, 5, 'Prijazno in sproščeno vzdušje, primeren kraj za oddih.', 4, TIMESTAMP '2022-11-10 11:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (3, 1, 'Kulturno bogastvo in čudovita arhitektura.', 5, TIMESTAMP '2023-07-03 15:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (3, 2, 'Vedno se vrnem sem, čaroben kraj.', 5, TIMESTAMP '2022-03-28 19:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (3, 3, 'Ponuja raznoliko izkušnjo za vse generacije.', 4, TIMESTAMP '2021-09-15 08:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (3, 4, 'Še ena pozitivna izkušnja, priporočam vsem!', 5, TIMESTAMP '2023-04-08 14:15:00');

-- User ID 4
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (4, 1, 'Preprosto čudovito, ničesar ne bi spremenil.', 5, TIMESTAMP '2022-10-20 16:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (4, 2, 'Včasih gneča, ampak vredno za lepe poglede.', 4, TIMESTAMP '2023-06-05 20:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (4, 3, 'Raznolika kulinarika, vendar bi lahko ponudba še boljša.', 3, TIMESTAMP '2022-02-18 09:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (4, 4, 'Pozitivna izkušnja, a prostor za izboljšave.', 4, TIMESTAMP '2021-07-12 13:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (4, 5, 'Prijazno osebje, vendar nekoliko predrago za ponujeno.', 3, TIMESTAMP '2023-01-25 17:30:00');

-- User ID 5
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (5, 2, 'Popoln kraj za ljubitelje zimskih športov, vedno se vrnem!', 5, TIMESTAMP '2022-08-15 10:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (5, 3, 'Edinstvena destinacija s čudovito naravo.', 4, TIMESTAMP '2023-03-01 14:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (5, 4, 'Prijeten kraj za pobeg od vsakdanjega stresa.', 4, TIMESTAMP '2022-11-12 19:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (5, 5, 'Za ljubitelje aktivnih počitnic, odlična izbira.', 5, TIMESTAMP '2023-06-28 11:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (5, 1, 'Enostavno najboljša destinacija za zimske užitke!', 5, TIMESTAMP '2022-02-10 15:00:00');

-- User ID 1
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 3, 'Včeraj sem se vrnil s čudovitega potovanja v ta osupljiv kraj. Osebje je bilo prijazno in ustrežljivo. Družinsko vzdušje in udobne nastanitve so pripomogli k temu, da sem se počutil kot doma. Zagotovo se bom še vrnil!', 5, TIMESTAMP '2023-12-10 15:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 4, 'Moje zadnje doživetje tukaj je bilo brezhibno! Užival sem v vsakem trenutku, od okusne hrane do osupljivih razgledov. Sobe so bile udobne in dobro opremljene. Ne morem si predstavljati boljšega kraja za sprostitev.', 5, TIMESTAMP '2022-08-25 12:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 2, 'Tokrat sem se odločil raziskati vsak kotiček te čudovite destinacije. Mesto je polno zgodovinskih znamenitosti in lokalnih obrtnikov. Moje doživetje je bilo resnično neponovljivo in ga bom zagotovo delil s prijatelji.', 4, TIMESTAMP '2023-06-05 18:20:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 5, 'Vračam se s svojega najnovejšega izleta in moram reči, da sem bil navdušen nad vsem. Sprehajal sem se po slikovitih poteh, užival v lokalnih dobrotah in spoznal prijazne domačine. Zagotovo se bom še vrnil!', 4, TIMESTAMP '2022-11-30 09:10:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (1, 1, 'Še en nepozaben izlet v Kranjsko Goro! Osebje v nastanitvah je bilo prijazno in ustrežljivo. Zagotovo se bom še vrnil, saj ta kraj nikoli ne razočara!', 5, TIMESTAMP '2023-03-15 14:45:00');

-- User ID 2
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 4, 'Naša družina je nedavno preživela počitnice v tem prelepem kraju. Otroci so uživali v raznolikih aktivnostih. Osebje je bilo prijazno in pozorno. Sobe so bile prostorne in udobne. Toplo priporočamo vsem družinam!', 4, TIMESTAMP '2022-10-10 11:30:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 5, 'Čudovito potovanje v mirno okolje! Naš oddih je bil poln sprehodov po naravi, raziskovanja lokalne kulinarike in sprostitve ob sončnih zahodih. Resnično smo uživali in se že veselimo naslednjega obiska.', 5, TIMESTAMP '2023-01-22 17:45:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 1, 'Kranjska Gora nas je znova navdušila s svojo lepoto in pestro ponudbo. Resnično smo uživali in se veselimo ponovnega obiska!', 4, TIMESTAMP '2023-05-18 20:15:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 2, 'Zadnjič smo preživeli čudovite trenutke v tem kraju. Sobe so bile udobne in moderno opremljene. Lokacija je bila odlična, saj smo bili blizu naravnih znamenitosti in zabavnih dejavnosti.', 3, TIMESTAMP '2022-04-05 14:00:00');
INSERT INTO priporocilni_metadata (user_id, lokacija_id, komentar, ocena, ustvarjen) VALUES (2, 3, 'Očarljiva destinacija, primerna za vso družino. Lokalne znamenitosti so čudovite. Osebje je prijazno. Priporočamo vsem!', 3, TIMESTAMP '2021-12-15 09:30:00');

