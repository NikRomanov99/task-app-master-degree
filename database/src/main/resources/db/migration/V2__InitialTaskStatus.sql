CREATE TABLE public.task_status
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT
);

INSERT INTO public.task_status(name, description)
VALUES ('Поставлена', 'Задача поставлена работнику');
INSERT INTO public.task_status(name, description)
VALUES ('В работе', 'Задача в процессе выполнения');
INSERT INTO public.task_status(name, description)
VALUES ('Выполнена', 'Задача выполнена');
INSERT INTO public.task_status(name, description)
VALUES ('Проверка результата', 'Проверка результатов задачи');
INSERT INTO public.task_status(name, description)
VALUES ('Возвращена', 'Задача возвращена в работу постановщиком');