insert into cuddle.role (id, name)
values  (1, 'ROLE_ADMIN'),
        (2, 'ROLE_VETENARY'),
        (3, 'ROLE_STAFF');

insert into cuddle.user (id, email, first_name, last_name, password, role_id)
values  (1, 'antoine.lestrade@he-arc.ch', 'Antoine', 'Lestrade', '$2a$10$NdRzrZqFx/pvjWXbtHkcWeMrRtWXWQSX7HYDfTzVCZJjUXsfx.VJ2', 1),
        (2, 'valentin.wyss@he-arc.ch', 'Valentin', 'Wyss', '$2a$10$.jb1IuVbrRh.4nb3YU71zeRPChsKVZgMwDG.UkJ1UAQEaosmNa5gC', 2),
        (3, 'florian.feuillade@he-arc.ch', 'Florian', 'Feuillade', '$2a$10$98YEvppXlj8NqV0C.USCb.O6vmUM35JpQ0R5sM5C7O3UyN6yW2JYW', 3);

insert into cuddle.species (id, name)
values  (1, 'Cat'),
        (2, 'Dog'),
        (3, 'Hamster'),
        (4, 'Rabbit');

insert into cuddle.breed (id, name)
values  (1, 'British Shorthair'),
        (2, 'Siberian'),
        (3, 'Ragdoll'),
        (4, 'European'),
        (5, 'Shiba'),
        (6, 'Turkish Angora');

insert into cuddle.animal (id, breed_id, species_id, name, image, age, description, sex)
values  (1, 1, 1, 'Billy', 'https://cdn.discordapp.com/attachments/782614625334067200/833629473861271602/unnamed.jpg', 12, 'https://www.youtube.com/channel/UCGMTesZlKa0Lokb7ZNqOJXQ', 'Female'),
        (2, 5, 2, 'Doge', 'https://i.kym-cdn.com/photos/images/newsfeed/000/581/296/c09.jpg', 15, 'Much wow', 'Female'),
        (3, 2, 1, 'Yuri', 'https://cdn.discordapp.com/attachments/782614625334067200/823499339283562506/IMG_20210129_114017.jpg', 1, 'Beau gosse', 'Male'),
        (4, 3, 1, 'Miwa', 'https://pbs.twimg.com/media/Ef0eeoqXgAAyq7c?format=jpg&name=large', 5, 'Live in a trashcan', 'Female'),
        (5, 4, 1, 'Chaplin', 'https://cdn.discordapp.com/attachments/782614625334067200/823500552389394472/XBz96_8AlAmRpt64P3_FY7uTnzgOIEtEYvSYNRjn6yVuQL5FpSnFmYp1NGC2k2DpvaJYPQcr-tPbQvEi0hxadhU6lCVmf4n8O6jp.png', 14, '<33', 'Male'),
        (6, 4, 1, 'Sunny', 'https://cdn.discordapp.com/attachments/782614625334067200/823499654036717579/IMG_20200730_205303.jpg', 10, 'yo', 'Female'),
        (7, 6, 1, 'Flocon', 'https://cdn.discordapp.com/attachments/782614625334067200/833635494457376778/unknown.png', 30, 'Damn he''s old boi', 'Male / Femae idk');