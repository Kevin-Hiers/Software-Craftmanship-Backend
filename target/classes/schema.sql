DROP TABLE IF EXISTS THREAT_INTEL_TAGS;
DROP TABLE IF EXISTS THREAT_INTEL_SCREENSHOT_PATHS;
DROP TABLE IF EXISTS URLHAUS_INTEL;
DROP TABLE IF EXISTS OPENPHISH_INTEL;
DROP TABLE IF EXISTS NETCRAFT_INTEL;
DROP TABLE IF EXISTS THREAT_INTEL;

DROP TABLE IF EXISTS CONTACTS;
DROP TABLE IF EXISTS DATA_SOURCE_STATUS;

CREATE TABLE THREAT_INTEL (
    id BIGSERIAL PRIMARY KEY,
    url VARCHAR(2047) NOT NULL,
    date_added VARCHAR(2047),
    url_status VARCHAR(2047),
    source VARCHAR(2047) NOT NULL,
    CONSTRAINT uq_threatintel_url_source UNIQUE (url, source)
);

-- Child table in JOINED inheritance: shares primary key with THREAT_INTEL
CREATE TABLE URLHAUS_INTEL (
    id BIGINT PRIMARY KEY,
    last_online VARCHAR(2047),
    threat VARCHAR(2047),
    urlhaus_link VARCHAR(2047),
    reporter VARCHAR(2047),
    CONSTRAINT fk_urlhaus_threatintel FOREIGN KEY (id) REFERENCES THREAT_INTEL(id) ON DELETE CASCADE
);

-- Child table in JOINED inheritance: shares primary key with THREAT_INTEL
CREATE TABLE OPENPHISH_INTEL (
    id BIGINT PRIMARY KEY,
    brand VARCHAR(255),
    ip VARCHAR(255),
    asn VARCHAR(255),
    asn_name VARCHAR(255),
    country_code VARCHAR(10),
    country_name VARCHAR(255),
    tld VARCHAR(255),
    family_id VARCHAR(255),
    host VARCHAR(255),
    isotime VARCHAR(255),
    page_language VARCHAR(255),
    ssl_cert_issued_by VARCHAR(1023),
    ssl_cert_issued_to VARCHAR(1023),
    ssl_cert_serial VARCHAR(1023),
    is_spear VARCHAR(10),
    sector VARCHAR(255),
    CONSTRAINT fk_openphish_threatintel FOREIGN KEY (id) REFERENCES THREAT_INTEL(id) ON DELETE CASCADE
);

-- Child table in JOINED inheritance: shares primary key with THREAT_INTEL
CREATE TABLE NETCRAFT_INTEL (
    id BIGINT PRIMARY KEY,
    netcraft_id VARCHAR(255),
    group_id VARCHAR(255),
    attack_url VARCHAR(2047),
    reported_url VARCHAR(2047),
    ip VARCHAR(255),
    country_code VARCHAR(10),
    last_updated VARCHAR(255),
    region VARCHAR(255),
    target_brand VARCHAR(255),
    auth_given BOOLEAN,
    host VARCHAR(255),
    registrar VARCHAR(255),
    customer_label VARCHAR(255),
    date_authed VARCHAR(255),
    stop_monitoring_date VARCHAR(255),
    domain VARCHAR(255),
    language VARCHAR(10),
    date_first_actioned VARCHAR(255),
    escalated BOOLEAN,
    first_contact VARCHAR(255),
    first_inactive VARCHAR(255),
    is_redirect VARCHAR(50),
    attack_type VARCHAR(100),
    deceptive_domain_score DOUBLE PRECISION,
    domain_risk_rating DOUBLE PRECISION,
    final_resolved VARCHAR(255),
    first_resolved VARCHAR(255),
    hostname VARCHAR(255),
    evidence_url VARCHAR(1023),
    domain_attack VARCHAR(50),
    false_positive BOOLEAN,
    hostname_attack VARCHAR(50),
    report_source VARCHAR(255),
    reporter VARCHAR(255),
    screenshot_url VARCHAR(1023),
    netcraft_status VARCHAR(100),
    CONSTRAINT fk_netcraft_threatintel FOREIGN KEY (id) REFERENCES THREAT_INTEL(id) ON DELETE CASCADE
);

CREATE TABLE THREAT_INTEL_TAGS (
    threat_id BIGINT NOT NULL,
    tag VARCHAR(255) NOT NULL,
    CONSTRAINT fk_tags_threatintel FOREIGN KEY (threat_id) REFERENCES THREAT_INTEL(id) ON DELETE CASCADE
);

CREATE TABLE THREAT_INTEL_SCREENSHOT_PATHS (
    threat_id BIGINT,
    screenshot_path VARCHAR(300),
    FOREIGN KEY (threat_id) REFERENCES THREAT_INTEL(id) ON DELETE CASCADE
);

CREATE INDEX IDX_THREAT_TAGS_THREAT_ID ON THREAT_INTEL_TAGS(threat_id);

CREATE TABLE CONTACTS(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(255),
    ROLE VARCHAR(255),
    EMAIL VARCHAR(255),
    PHONE VARCHAR(255)
);

CREATE TABLE DATA_SOURCE_STATUS (
    source VARCHAR(255) PRIMARY KEY,
    last_updated VARCHAR(255),
    last_new_count INTEGER
);

