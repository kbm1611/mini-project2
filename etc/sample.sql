# 게임명: 화투로
drop database if exists hwaturo;
create database hwaturo;
use hwaturo;

# 유저정보를 담는 users테이블 생성
create table user(
	user_no int auto_increment,
    constraint primary key( user_no ),
    uid varchar(30) not null unique,
    upwd varchar(30) not null,
    nickname varchar(20) not null unique
);

# 부적,점괘 같은 아이템을 담는 item_info테이블 생성
create table item_info(
	item_no int auto_increment,
    constraint primary key( item_no ),
    name varchar(30) not null,
    type varchar(10) not null, 
    price int not null,
    description varchar(100),
    effect_code varchar(20)
);

#게임의 랭킹, 전적을 확인할 수 있게 하는 game_log테이블 생성
create table game_log(
	log_no int auto_increment,
    constraint primary key( log_no ),
    user_no int,
    constraint foreign key ( user_no ) references user( user_no ),
    play_date datetime default now(),
    final_score int default 1,
    final_round int default 1,
    final_money int default 1
);

#유저의 정보를 저장하는 save_file테이블 생성
create table save_file(
	user_no int not null,
    constraint primary key ( user_no ),
    constraint foreign key ( user_no ) references user( user_no ),
    current_round int default 1,
    current_hp int default 3,
    current_money int default 0,
    current_score int default 0,
    card varchar(1000),
    item varchar(500)
);

-- user 테이블 샘플 데이터
INSERT INTO user (user_no, uid, upwd, nickname) VALUES
(1, 'admin', '1234', '관리자1'),
(2, 'kafell', '141543', '아저씨1'),
(3, 'ganatech', 'pw123', '가나전력'),
(4, 'topline', 'pw432', '탑라인'),
(5, 'sunnet', 'pw000', '썬네트'),
(6, 'wireguy', 'pw001', '와이어맨'),
(7, 'junil', 'pw4321', '준일통신'),
(8, 'jinyoung', 'pw876', '진영씨'),
(9, 'datahero', 'pw555', '데이터영웅'),
(10, 'jhwang', 'pw789', '황사장');

-- item_info 테이블 샘플 데이터
INSERT INTO item_info (item_no, name, type, price, description, effect_code) VALUES
(1, '호랑이 기운', '부적', 150, '동물(열) 패를 낼 때마다 점수 +10', 'PASSIVE_ANIMAL_BOOST'),
(2, '재물 부적', '부적', 200, '판이 끝날 때 마다 획득 엽전 1.5배', 'PASSIVE_MONEY_UP'),
(3, '광기 부적', '부적', 300, '모든 광의 기본 점수가 2배가 된다', 'PASSIVE_KWANG_DOUBLE'),
(4, '신령님의 계시', '점괘', 80, '내 손패 1장을 랜덤하게 광으로 바꾼다', 'ACTIVE_CHANGE_KWANG'),
(5, '밑장 빼기', '점괘', 50, '현재 손패를 모두 버리고 다시 뽑는다', 'ACTIVE_REDRAW_ALL'),
(6, '조상님의 도움', '점괘', 100, '다음 족보 배수를 +3배 추가한다', 'ACTIVE_NEXT_MULT_UP'),
(7, '동작 그만', '점괘', 300, '지금 패를 다음 판에도 유지한다', 'ACTIVE_KEEP_HAND'),
(8, '붉은 띠', '부적', 100, '홍단 점수 +3배', 'PASSIVE_RED_MULT_UP'),
(9, '푸른 띠', '부적', 100, '청단 점수 +3배', 'PASSIVE_BLUE_MULT_UP'),
(10, '아수라발발타', '점괘', 400, '목숨을 3으로 만든다', 'ACTIVE_ASURA_HP');

-- game_log 테이블 샘플 데이터
INSERT INTO game_log (log_no, user_no, play_date, final_score, final_round, final_money) VALUES
(1, 1, '2026-02-09', 9899, 8, 400),
(2, 1, '2026-02-09', 1542, 2, 250),
(3, 2, '2026-02-08', 1651, 2, 100),
(4, 2, '2026-02-08', 2975, 4, 400),
(5, 5, '2026-02-07', 8132, 7, 300),
(6, 3, '2026-02-07', 3206, 4, 200),
(7, 4, '2026-02-06', 1823, 2, 100),
(8, 5, '2026-02-06', 8912, 8, 150),
(9, 6, '2026-02-06', 5356, 6, 180),
(10, 6, '2026-02-05', 4954, 5, 220);

-- save_file 테이블 샘플 데이터
INSERT INTO save_file (user_no, current_round, current_hp, current_money, current_score, card, item) VALUES
(1, 3, 3, 400, 1400, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '5, 4'),
(2, 4, 3, 250, 1800, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '1, 6'),
(3, 2, 3, 100, 600, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '2'),
(4, 5, 3, 400, 3000, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '5, 4'),
(5, 6, 2, 300, 3500, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '1, 1'),
(6, 1, 3, 320, 200, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '1, 4'),
(7, 2, 3, 280, 600, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '4'),
(8, 4, 3, 333, 1400, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '8, 4'),
(9, 5, 3, 444, 2600, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '9, 4'),
(10, 1, 3, 555, 100, '1, 3, 10, 22, 38, 40, 41, 42, 44, 48', '1, 10');