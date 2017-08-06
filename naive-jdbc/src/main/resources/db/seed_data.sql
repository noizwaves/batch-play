TRUNCATE TABLE source.widget;
INSERT INTO source.widget (id, name, unit_price)
    VALUES (1, 'Coffee Mug, Colorado style', 6.99);

TRUNCATE TABLE source.gizmo;
INSERT INTO source.gizmo (id, creator_name, creator_type, cost)
    VALUES (4, 'Dan Hamster', 'G', 23);

TRUNCATE TABLE source.gizmo;
INSERT INTO source.gizmo (id, creator_name, creator_type, cost)
    VALUES (1, 'Nikola Tesla', 'IN', 33);
