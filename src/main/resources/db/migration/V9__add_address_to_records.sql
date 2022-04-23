DELETE FROM orders;

INSERT INTO orders (id, description, ending_time, latitude, longitude, radius, starting_time, manager_id, address)
    VALUES ('1', 'Some description', '2022-05-13 19:08:00', '53.906458', '27.559618', '500', '2022-05-13 08:00:00', '1', 'Zybitskaya st.'),
           ('2', 'Another description', '2022-04-26 20:00:00', '53.912344', '27.594372', '750', '2022-04-26 08:00:00', '1', 'Gikalo 9 st.');