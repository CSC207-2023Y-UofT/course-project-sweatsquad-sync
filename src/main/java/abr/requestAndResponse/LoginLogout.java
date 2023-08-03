package abr.requestAndResponse;

import abr.ResponseModel;

public enum LoginLogout implements ResponseModel {
    LOGIN_REGULAR_USER,
    LOGIN_INSTRUCTOR,

    LOGIN_ADMIN,

    LOGOUT;
}
