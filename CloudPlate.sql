CREATE TABLE tab_user (
  uid int(11) NOT NULL AUTO_INCREMENT,
  uname varchar(11) DEFAULT NULL COMMENT '不能重复的用户名',
  usex char(1) DEFAULT NULL,
  upwd varchar(50) DEFAULT NULL,
  ureal_name varchar(11) DEFAULT NULL,
  uscore int(11) DEFAULT NULL COMMENT '用户积分',
  usize int(11) DEFAULT NULL COMMENT '网盘大小',
  ustatus int(11) DEFAULT NULL COMMENT '1可用，0已经登录，-1冻结',
  PRIMARY KEY (uid),
  UNIQUE KEY uname (uname)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

CREATE TABLE tab_my_file (
  fid int(11) NOT NULL AUTO_INCREMENT,
  furl varchar(100) DEFAULT NULL COMMENT '文件路径',
  fmd5 varchar(50) DEFAULT NULL COMMENT '文件妙传的md5',
  fsize int(11) DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (fid)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8

CREATE TABLE tab_my_directory (
  did int(11) NOT NULL AUTO_INCREMENT,
  dname varchar(50) DEFAULT NULL COMMENT '文件夹名字或文件名字',
  dtype int(11) DEFAULT NULL COMMENT '文件1，文件夹0',
  dtime date DEFAULT NULL COMMENT '创建时间',
  uid int(11) DEFAULT NULL,
  dfu varchar(100) DEFAULT NULL COMMENT '文件路径：0-1-3-1',
  dfid int(11) DEFAULT NULL COMMENT '如果是文件夹值为null，如果是文件指向文件id',
  dsize int(11) DEFAULT NULL,
  dprivate int(11) DEFAULT NULL COMMENT '文件权限，0表示私有，1表示公开',
  PRIMARY KEY (did)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8
