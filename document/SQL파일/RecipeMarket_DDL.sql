/* 구매 */
CREATE TABLE purchase (
	purchase_code NUMBER(6) NOT NULL, /* 구매번호 */
	customer_email VARCHAR2(40), /* 유저이메일 */
	purchase_date DATE /* 구매날짜 */
);

COMMENT ON TABLE purchase IS '구매';

COMMENT ON COLUMN purchase.purchase_code IS '구매번호';

COMMENT ON COLUMN purchase.customer_email IS '유저이메일';

COMMENT ON COLUMN purchase.purchase_date IS '구매날짜';

ALTER TABLE purchase
	ADD
		CONSTRAINT PK_purchase
		PRIMARY KEY (
			purchase_code
		);

/* 레시피재료정보 */
CREATE TABLE ingredient (
	ing_code NUMBER(6) NOT NULL, /* 재료코드 */
	ing_name VARCHAR2(50) /* 재료이름 */
);

COMMENT ON TABLE ingredient IS '레시피재료정보';

COMMENT ON COLUMN ingredient.ing_code IS '재료코드';

COMMENT ON COLUMN ingredient.ing_name IS '재료이름';

ALTER TABLE ingredient
	ADD
		CONSTRAINT PK_ingredient
		PRIMARY KEY (
			ing_code
		);

/* 레시피재료 */
CREATE TABLE recipe_ingredient (
	recipe_code NUMBER(6) NOT NULL, /* 레시피코드 */
	ing_code NUMBER(6) NOT NULL /* 재료코드 */
);

COMMENT ON TABLE recipe_ingredient IS '레시피재료';

COMMENT ON COLUMN recipe_ingredient.recipe_code IS '레시피코드';

COMMENT ON COLUMN recipe_ingredient.ing_code IS '재료코드';

ALTER TABLE recipe_ingredient
	ADD
		CONSTRAINT PK_recipe_ingredient
		PRIMARY KEY (
			recipe_code,
			ing_code
		);

/* 레시피기본정보 */
CREATE TABLE recipe_info (
	recipe_code NUMBER(6) NOT NULL, /* 레시피코드 */
	recipe_name VARCHAR2(40), /* 레시피이름 */
	recipe_summ VARCHAR2(250), /* 레시피요약 */
	recipe_price NUMBER(10), /* 레시피가격 */
	recipe_process VARCHAR2(100), /* 레시피과정경로 */
	img_url VARCHAR2(200), /* 이미지경로 */
	rd_email VARCHAR2(40), /* 연구원이메일 */
	recipe_status CHAR(1) /* 레시피활성화여부 */
);

COMMENT ON TABLE recipe_info IS '레시피기본정보';

COMMENT ON COLUMN recipe_info.recipe_code IS '레시피코드';

COMMENT ON COLUMN recipe_info.recipe_name IS '레시피이름';

COMMENT ON COLUMN recipe_info.recipe_summ IS '레시피요약';

COMMENT ON COLUMN recipe_info.recipe_price IS '레시피가격';

COMMENT ON COLUMN recipe_info.recipe_process IS '레시피과정경로';

COMMENT ON COLUMN recipe_info.img_url IS '이미지경로';

COMMENT ON COLUMN recipe_info.rd_email IS '연구원이메일';

COMMENT ON COLUMN recipe_info.recipe_status IS '레시피활성화여부';

ALTER TABLE recipe_info
	ADD
		CONSTRAINT PK_recipe_info
		PRIMARY KEY (
			recipe_code
		);

/* 즐겨찾기 */
CREATE TABLE favorite (
	customer_email VARCHAR2(40) NOT NULL, /* 유저이메일 */
	recipe_code NUMBER(6) NOT NULL /* 레시피코드 */
);

COMMENT ON TABLE favorite IS '즐겨찾기';

COMMENT ON COLUMN favorite.customer_email IS '유저이메일';

COMMENT ON COLUMN favorite.recipe_code IS '레시피코드';

ALTER TABLE favorite
	ADD
		CONSTRAINT PK_favorite
		PRIMARY KEY (
			customer_email,
			recipe_code
		);

/* 관리자 */
CREATE TABLE admin (
	admin_id VARCHAR2(40) NOT NULL, /* 관리자아이디 */
	admin_pwd VARCHAR2(20) /* 비밀번호 */
);

COMMENT ON TABLE admin IS '관리자';

COMMENT ON COLUMN admin.admin_id IS '관리자아이디';

COMMENT ON COLUMN admin.admin_pwd IS '비밀번호';

ALTER TABLE admin
	ADD
		CONSTRAINT PK_admin
		PRIMARY KEY (
			admin_id
		);

/* 유저 */
CREATE TABLE customer (
	customer_email VARCHAR2(40) NOT NULL, /* 이메일 */
	customer_pwd VARCHAR2(20), /* 비밀번호 */
	customer_name VARCHAR2(50), /* 이름 */
	customer_gender CHAR(1), /* 성별 */
	customer_birth_date DATE, /* 생년월일 */
	customer_phone VARCHAR2(30), /* 전화번호 */
	buildingno VARCHAR2(25), /* 건물관리번호 */
	customer_addr VARCHAR2(50), /* 상세주소 */
	verification CHAR(1), /* 인증여부 */
	customer_status CHAR(1) /* 활성화여부 */
);

COMMENT ON TABLE customer IS '유저';

COMMENT ON COLUMN customer.customer_email IS '이메일';

COMMENT ON COLUMN customer.customer_pwd IS '비밀번호';

COMMENT ON COLUMN customer.customer_name IS '이름';

COMMENT ON COLUMN customer.customer_gender IS '성별';

COMMENT ON COLUMN customer.customer_birth_date IS '생년월일';

COMMENT ON COLUMN customer.customer_phone IS '전화번호';

COMMENT ON COLUMN customer.buildingno IS '건물관리번호';

COMMENT ON COLUMN customer.customer_addr IS '상세주소';

COMMENT ON COLUMN customer.verification IS '인증여부';

COMMENT ON COLUMN customer.customer_status IS '활성화여부';

ALTER TABLE customer
	ADD
		CONSTRAINT PK_customer
		PRIMARY KEY (
			customer_email
		);

/* 연구원 */
CREATE TABLE rd (
	rd_email VARCHAR2(40) NOT NULL, /* 연구원이메일 */
	rd_pwd VARCHAR2(20), /* 비밀번호 */
	rd_manager_name VARCHAR2(50), /* 담당자이름 */
	rd_team_name VARCHAR2(50), /* 부서이름 */
	rd_phone VARCHAR2(30), /* 전화번호 */
	rd_status CHAR(1) /* 활성화여부 */
);

COMMENT ON TABLE rd IS '연구원';

COMMENT ON COLUMN rd.rd_email IS '연구원이메일';

COMMENT ON COLUMN rd.rd_pwd IS '비밀번호';

COMMENT ON COLUMN rd.rd_manager_name IS '담당자이름';

COMMENT ON COLUMN rd.rd_team_name IS '부서이름';

COMMENT ON COLUMN rd.rd_phone IS '전화번호';

COMMENT ON COLUMN rd.rd_status IS '활성화여부';

ALTER TABLE rd
	ADD
		CONSTRAINT PK_rd
		PRIMARY KEY (
			rd_email
		);

/* 좋아요싫어요 */
CREATE TABLE point (
	recipe_code NUMBER(6) NOT NULL, /* 레시피코드 */
	like_count NUMBER(5), /* 좋아요개수 */
	dislike_count NUMBER(5) /* 싫어요개수 */
);

COMMENT ON TABLE point IS '좋아요싫어요';

COMMENT ON COLUMN point.recipe_code IS '레시피코드';

COMMENT ON COLUMN point.like_count IS '좋아요개수';

COMMENT ON COLUMN point.dislike_count IS '싫어요개수';

ALTER TABLE point
	ADD
		CONSTRAINT PK_point
		PRIMARY KEY (
			recipe_code
		);

/* 구매상세 */
CREATE TABLE purchase_detail (
	purchase_code NUMBER(6) NOT NULL, /* 구매번호 */
	recipe_code NUMBER(6) NOT NULL, /* 레시피코드 */
	purchase_quantity NUMBER(10) /* 수량 */
);

COMMENT ON TABLE purchase_detail IS '구매상세';

COMMENT ON COLUMN purchase_detail.purchase_code IS '구매번호';

COMMENT ON COLUMN purchase_detail.recipe_code IS '레시피코드';

COMMENT ON COLUMN purchase_detail.purchase_quantity IS '수량';

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT PK_purchase_detail
		PRIMARY KEY (
			purchase_code,
			recipe_code
		);

/* 후기 */
CREATE TABLE review (
	purchase_code NUMBER(6) NOT NULL, /* 구매번호 */
	review_comment VARCHAR2(150), /* 후기내용 */
	review_date DATE /* 작성일자 */
);

COMMENT ON TABLE review IS '후기';

COMMENT ON COLUMN review.purchase_code IS '구매번호';

COMMENT ON COLUMN review.review_comment IS '후기내용';

COMMENT ON COLUMN review.review_date IS '작성일자';

ALTER TABLE review
	ADD
		CONSTRAINT PK_review
		PRIMARY KEY (
			purchase_code
		);

/* 우편번호 */
CREATE TABLE postal (
	buildingno VARCHAR2(25) NOT NULL, /* 건물관리번호 */
	zipcode VARCHAR2(5), /* 우편번호 */
	sido VARCHAR2(21), /* 시도 */
	sigungu VARCHAR2(20), /* 시군구 */
	eupmyun VARCHAR2(20), /* 읍면동 */
	dorocode VARCHAR2(12), /* 도로명코드 */
	doro VARCHAR2(80), /* 도로명 */
	jiha VARCHAR2(1), /* 지하여부 */
	building1 VARCHAR2(5), /* 건물번호본번 */
	building2 VARCHAR2(5), /* 건물번호부번 */
	daryang VARCHAR2(40), /* 다량배달처명 */
	building VARCHAR2(200), /* 시군구용건물명 */
	dongcode VARCHAR2(10), /* 법정동코드 */
	dong VARCHAR2(20), /* 법정동명 */
	ri VARCHAR2(20), /* 리명 */
	dongadmin VARCHAR2(40), /* 행정동명 */
	san VARCHAR2(1), /* 산여부 */
	zibun1 VARCHAR2(4), /* 지번본번 */
	zibunserial VARCHAR2(2), /* 읍면동일련번호 */
	zibun2 VARCHAR2(4), /* 지번부번 */
	zipoldcode VARCHAR2(6), /* 구우편번호 */
	zipcodeserial VARCHAR2(3) /* 우편번호일련번호 */
);

COMMENT ON TABLE postal IS '우편번호';

COMMENT ON COLUMN postal.buildingno IS '건물관리번호';

COMMENT ON COLUMN postal.zipcode IS '우편번호';

COMMENT ON COLUMN postal.sido IS '시도';

COMMENT ON COLUMN postal.sigungu IS '시군구';

COMMENT ON COLUMN postal.eupmyun IS '읍면동';

COMMENT ON COLUMN postal.dorocode IS '도로명코드';

COMMENT ON COLUMN postal.doro IS '도로명';

COMMENT ON COLUMN postal.jiha IS '지하여부';

COMMENT ON COLUMN postal.building1 IS '건물번호본번';

COMMENT ON COLUMN postal.building2 IS '건물번호부번';

COMMENT ON COLUMN postal.daryang IS '다량배달처명';

COMMENT ON COLUMN postal.building IS '시군구용건물명';

COMMENT ON COLUMN postal.dongcode IS '법정동코드';

COMMENT ON COLUMN postal.dong IS '법정동명';

COMMENT ON COLUMN postal.ri IS '리명';

COMMENT ON COLUMN postal.dongadmin IS '행정동명';

COMMENT ON COLUMN postal.san IS '산여부';

COMMENT ON COLUMN postal.zibun1 IS '지번본번';

COMMENT ON COLUMN postal.zibunserial IS '읍면동일련번호';

COMMENT ON COLUMN postal.zibun2 IS '지번부번';

COMMENT ON COLUMN postal.zipoldcode IS '구우편번호';

COMMENT ON COLUMN postal.zipcodeserial IS '우편번호일련번호';

ALTER TABLE postal
	ADD
		CONSTRAINT postal_buildingno_pk
		PRIMARY KEY (
			buildingno
		);

/* 장바구니 */
CREATE TABLE cart (
	customer_email VARCHAR2(40) NOT NULL, /* 이메일 */
	recipe_code NUMBER(6) NOT NULL, /* 레시피코드 */
	cart_quantity NUMBER(10) /* 수량 */
);

COMMENT ON TABLE cart IS '장바구니';

COMMENT ON COLUMN cart.customer_email IS '이메일';

COMMENT ON COLUMN cart.recipe_code IS '레시피코드';

COMMENT ON COLUMN cart.cart_quantity IS '수량';

ALTER TABLE cart
	ADD
		CONSTRAINT PK_cart
		PRIMARY KEY (
			customer_email,
			recipe_code
		);

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_customer_TO_purchase
		FOREIGN KEY (
			customer_email
		)
		REFERENCES customer (
			customer_email
		);

ALTER TABLE recipe_ingredient
	ADD
		CONSTRAINT recipe_code_fk
		FOREIGN KEY (
			recipe_code
		)
		REFERENCES recipe_info (
			recipe_code
		);

ALTER TABLE recipe_ingredient
	ADD
		CONSTRAINT ing_code_fk
		FOREIGN KEY (
			ing_code
		)
		REFERENCES ingredient (
			ing_code
		);

ALTER TABLE recipe_info
	ADD
		CONSTRAINT rd_email_fk
		FOREIGN KEY (
			rd_email
		)
		REFERENCES rd (
			rd_email
		);

ALTER TABLE favorite
	ADD
		CONSTRAINT favorite_recipe_code_fk
		FOREIGN KEY (
			recipe_code
		)
		REFERENCES recipe_info (
			recipe_code
		);

ALTER TABLE favorite
	ADD
		CONSTRAINT favorite_customer_email_fk
		FOREIGN KEY (
			customer_email
		)
		REFERENCES customer (
			customer_email
		);

ALTER TABLE customer
	ADD
		CONSTRAINT customer_buildingno_fk
		FOREIGN KEY (
			buildingno
		)
		REFERENCES postal (
			buildingno
		);

ALTER TABLE point
	ADD
		CONSTRAINT point_recipe_code_fk
		FOREIGN KEY (
			recipe_code
		)
		REFERENCES recipe_info (
			recipe_code
		);

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT detail_purchase_code_fk
		FOREIGN KEY (
			purchase_code
		)
		REFERENCES purchase (
			purchase_code
		);

ALTER TABLE purchase_detail
	ADD
		CONSTRAINT detail_recipe_code_fk
		FOREIGN KEY (
			recipe_code
		)
		REFERENCES recipe_info (
			recipe_code
		);

ALTER TABLE review
	ADD
		CONSTRAINT review_purchase_code_fk
		FOREIGN KEY (
			purchase_code
		)
		REFERENCES purchase (
			purchase_code
		);

ALTER TABLE cart
	ADD
		CONSTRAINT cart_customer_email_fk
		FOREIGN KEY (
			customer_email
		)
		REFERENCES customer (
			customer_email
		);

ALTER TABLE cart
	ADD
		CONSTRAINT cart_recipe_code_fk
		FOREIGN KEY (
			recipe_code
		)
		REFERENCES recipe_info (
			recipe_code
		);