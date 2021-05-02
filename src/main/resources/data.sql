insert into cuddle.role (id, name)
values  (1, 'ROLE_ADMIN'),
        (2, 'ROLE_VETERINARY'),
        (3, 'ROLE_STAFF');

insert into cuddle.user (id, email, first_name, last_name, password, role_id)
values  (1, 'antoine.lestrade@he-arc.ch', 'Antoine', 'Lestrade', '$2a$10$NdRzrZqFx/pvjWXbtHkcWeMrRtWXWQSX7HYDfTzVCZJjUXsfx.VJ2', 1),
        (3, 'florian.feuillade@he-arc.ch', 'Florian', 'Feuillade', '$2a$10$98YEvppXlj8NqV0C.USCb.O6vmUM35JpQ0R5sM5C7O3UyN6yW2JYW', 3),
        (4, 'testVet@test.com', 'Test', 'Test', '$2a$10$5HcE0IsNC5Ak34bVsd.t.udD.PcefixSXBDztE6VIwNSwpCEulYKm', 2);

insert into cuddle.species (id, name)
values  (1, 'Cat'),
        (2, 'Dog');

insert into cuddle.breed (id, name)
values  (1, 'British Shorthair'),
        (2, 'Siberian'),
        (3, 'Ragdoll'),
        (4, 'European'),
        (5, 'Shiba'),
        (6, 'Turkish Angora'),
        (7, 'Labrador'),
        (8, 'Beagle'),
        (9, 'Norvegian');

insert into cuddle.animal (id, age, description, image, name, sex, treatment, breed_id, species_id)
values  (1, 2020, 'Très soyeux', '2506b461-50b8-4a5d-8326-b4f97b4b9ceb.png', 'Yuri', 'Male', 'Trois câlins par jour maximum', 2, 1),
        (2, 2009, 'Mad mad mad', 'c8a846c6-eeb9-482a-8385-d76bdc304401.png', 'Billy', 'Female', '1mg/jour de dafalgan', 1, 1),
        (3, 2007, '<3 <3 <3', 'aea908b5-cd9f-491d-b884-81038fce6f9b.png', 'Chaplin', 'Male', '', 4, 1),
        (4, 2006, 'Much wow', '3219b38e-5172-415f-a03d-9a2ec593c23c.png', 'Doge', 'Female', '', 5, 2),
        (5, 2010, 'Santa boy', '68e17c89-8773-4afc-9a6e-81970d7a0c7f.jpg', 'Falco', 'Male', '', 8, 2),
        (6, 2016, 'I live in a trashcan', '7d4545d2-ca34-494b-9530-3a5e8b3cfa64.jfif', 'Miwa', 'Female', '', 3, 1),
        (7, 2011, 'Yo ?', '3f8399d8-ecc7-45f9-8749-db5ed6619226.jpg', 'Sunny', 'Female', '', 9, 1),
        (8, 1991, 'Damn he''s old boi', '07bc5d89-516d-4f4b-8536-ccb184093fb3.png', 'Flocon', 'Male / Femae idk', '', 6, 1),
        (9, 2020, 'Quieres ?', '21c24e9d-a72c-41d3-aa73-94de0b753a1f.png', 'Perro', 'Male', '', 7, 2),
        (11, 2021, 'https://yokoshop.com/collections/all', '2edacace-c712-4b11-8e7c-b25ba657b87c.jpg', 'Yoko', 'Female', '', 4, 1),
        (12, 2021, 'Pyro barbare', '26e4f84c-a179-4d9e-87cb-dcda2e583100.jpg', 'Lennon', 'Male', '', 4, 1),
        (13, 2000, 'Black hole', 'f60256e6-92fe-4183-b384-de2c4900b8cf.jpg', 'Spike', 'Male', '', 4, 1),
        (14, 2001, 'Don''t mess with him', 'dc6938f6-d201-4d46-80ab-1cba95c47f16.jpg', 'Tigris', 'Male', '', 4, 1),
        (15, 2015, 'Calme, discret mais calin', '9a02fbf3-4ce9-455d-9258-f017287c67ee.png', 'Justin', 'Female', '', 6, 1);