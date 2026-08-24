USE ta_limpo;

-- =========================================================
-- 10 MARCAS
-- =========================================================

INSERT INTO marca (nome) VALUES
('Toyota'),
('Honda'),
('Volkswagen'),
('Chevrolet'),
('Ford'),
('Fiat'),
('Hyundai'),
('Nissan'),
('Renault'),
('Jeep');


-- =========================================================
-- 5 MODELOS POR MARCA
-- =========================================================

INSERT INTO veiculo (modelo, marca_id) VALUES

-- Toyota (1)
('Corolla', 1),
('Yaris', 1),
('Hilux', 1),
('Etios', 1),
('SW4', 1),

-- Honda (2)
('Civic', 2),
('City', 2),
('HR-V', 2),
('Fit', 2),
('CR-V', 2),

-- Volkswagen (3)
('Gol', 3),
('Polo', 3),
('Virtus', 3),
('T-Cross', 3),
('Taos', 3),

-- Chevrolet (4)
('Onix', 4),
('Tracker', 4),
('Cruze', 4),
('S10', 4),
('Spin', 4),

-- Ford (5)
('Ka', 5),
('Fiesta', 5),
('Focus', 5),
('Ranger', 5),
('EcoSport', 5),

-- Fiat (6)
('Argo', 6),
('Mobi', 6),
('Cronos', 6),
('Toro', 6),
('Strada', 6),

-- Hyundai (7)
('HB20', 7),
('Creta', 7),
('Tucson', 7),
('ix35', 7),
('Santa Fe', 7),

-- Nissan (8)
('Kicks', 8),
('Versa', 8),
('Sentra', 8),
('Frontier', 8),
('March', 8),

-- Renault (9)
('Kwid', 9),
('Sandero', 9),
('Logan', 9),
('Duster', 9),
('Oroch', 9),

-- Jeep (10)
('Renegade', 10),
('Compass', 10),
('Commander', 10),
('Wrangler', 10),
('Gladiator', 10);


-- =========================================================
-- 20 LAVAGENS
-- =========================================================

INSERT INTO lavagem
    (veiculo_id, placa, data, observacao, status)
VALUES

(1,  'ABC1A01', '2026-08-20', 'Lavagem completa', 'finalizado'),

(2,  'ABC1A02', '2026-08-20', 'Lavagem externa', 'finalizado'),

(3,  'ABC1A03', '2026-08-20', 'Limpeza interna e externa', 'finalizado'),

(4,  'ABC1A04', '2026-08-20', 'Veículo com muita sujeira', 'finalizado'),

(5,  'ABC1A05', '2026-08-20', 'Lavagem simples', 'finalizado'),

(6,  'ABC1B01', '2026-08-21', 'Lavagem completa', 'finalizado'),

(7,  'ABC1B02', '2026-08-21', 'Limpeza interna', 'finalizado'),

(8,  'ABC1B03', '2026-08-21', 'Lavagem externa', 'finalizado'),

(9,  'ABC1B04', '2026-08-21', 'Lavagem completa', 'finalizado'),

(10, 'ABC1B05', '2026-08-21', 'Lavagem simples', 'finalizado'),

(11, 'ABC1C01', '2026-08-22', 'Lavagem completa', 'finalizado'),

(12, 'ABC1C02', '2026-08-22', 'Limpeza interna e externa', 'finalizado'),

(13, 'ABC1C03', '2026-08-22', 'Lavagem externa', 'finalizado'),

(14, 'ABC1C04', '2026-08-22', 'Lavagem completa', 'finalizado'),

(15, 'ABC1C05', '2026-08-22', 'Veículo muito sujo', 'finalizado'),

(16, 'ABC1D01', '2026-08-24', 'Lavagem completa', 'lavando'),

(17, 'ABC1D02', '2026-08-24', 'Limpeza interna', 'lavando'),

(18, 'ABC1D03', '2026-08-24', 'Lavagem externa', 'aguardando'),

(19, 'ABC1D04', '2026-08-24', 'Lavagem completa', 'aguardando'),

(20, 'ABC1D05', '2026-08-24', 'Lavagem simples', 'aguardando');
