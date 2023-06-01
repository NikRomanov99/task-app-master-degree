CREATE TABLE public.task_info
(
    id                       BIGSERIAL PRIMARY KEY,
    name                     TEXT   NOT NULL,
    task_description         TEXT,
    r_task_type_id           BIGINT NOT NULL,
    r_task_status_id         BIGINT NOT NULL,
    r_user_executor_id       BIGINT NOT NULL,
    r_user_director_id       BIGINT NOT NULL,
    r_available_equipment_id BIGINT,
    start_task_date          TIMESTAMP,
    finish_task_date         TIMESTAMP,
    CONSTRAINT fk_task_type_id
        FOREIGN KEY (r_task_type_id)
            REFERENCES task_type (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_task_status_id
        FOREIGN KEY (r_task_status_id)
            REFERENCES task_status (id)
            ON DELETE CASCADE
);

INSERT INTO public.task_info(name, task_description, r_task_type_id, r_task_status_id, r_user_executor_id,
                             r_user_director_id, r_available_equipment_id, start_task_date, finish_task_date)
VALUES ('Плановая проверка цистерны', 'Просьба осмотреть указанную цистерну на предмет утечек', 1, 2, 7, 3, 1,
        '01-06-2023', '12-06-2023');