CREATE TABLE public.task_type
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT
);
INSERT INTO public.task_type(name, description)
VALUES ('Штатная плановая проверка оборудования', 'Регулярный осмотр оборудования');
INSERT INTO public.task_type(name, description)
VALUES ('Внештатная проверка оборудования', 'Внеплановый осмотр оборудования в связи со сбоем');