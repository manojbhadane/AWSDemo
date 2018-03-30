package com.takescreenshot_demo.app;

public class Constants {

    // QA/*
   public static String SERVER_URL="http://192.168.0.100:83/Inphormd/API/InphormdAPI";

    //UAT
   /* public static String SERVER_URL="http://13.59.138.246/InphormdAPI";*/

    //new uat
   /* public static String SERVER_URL = "http://api.inphormd.com/UAT/InphormdAPI";*/


    //Live
  /*  public static String SERVER_URL = "http://api.inphormd.com/InphormdAPI";*/

    //new DEV URL
  /*   public static String SERVER_URL="http://192.168.0.168:81/API/InphormdAPI";*/


    public static String SHAREDPREF_USETPROFILEPERCENTAGE = "UserProfilePercentage";
    public static String SHAREDPREF_FNAME = "user_firstname";
    public static String SHAREDPREF_LNAME = "user_lastname";
    public static String SHAREDPREF_CITY = "user_city";
    public static String SHAREDPREF_AGE = "user_age";
    public static String SHAREDPREF_GENDER = "user_gender";
    public static String SHAREDPREF_USERTYPE = "user_usertype";

    public static String SHAREDPREF_USERNAME = "user_username";
    public static String SHAREDPREF_PASSWORD = "user_password";
    public static String SHAREDPREF_LOGIN = "login_session";

    public static final String SHAREDPREF_USER_ID = "user_id";
    public static String SHAREDPREF_METRICS_LIST_DTM = "metrics_list_dtm";
    public static String SHAREDPREF_MEDICATION_LIST_DTM = "medication_list_dtm";
    public static String SHAREDPREF_GET_METRICS_LIST = "get_metrics_list_dtm";
    public static String SHAREDPREF_GET_CIRCLE_POST_LIST = "get_circle_post_list_dtm";

    public static final String SHAREDPREF_LASTCHECKDTM_PRIMARY_CONDITION = "last_checked_dtm_primarycondition";
    public static final String SHAREDPREF_LASTCHECKDTM_PRIMARY_CONDITION_STAGE = "last_checked_dtm_primarycondition_stage";
    public static final String SHAREDPREF_LASTCHECKDTM_SECONDARY_CONDITION = "last_checked_dtm_secondarycondition";
    public static final String SHAREDPREF_LASTCHECKDTM_COUNTRYLIST = "last_checked_dtm_countrylist";
    public static final String SHAREDPREF_LASTCHECKDTM_RESEARCHLIST = "last_checked_dtm_researchlist";
    public static final String SHAREDPREF_LASTCHECKDTM_USEREVENTLIST = "last_checked_dtm_usereventlist";
    public static final String SHAREDPREF_LASTCHECKDTM_PRESCRIPTIONLIST = "last_checked_dtm_prescriptionlist";
    public static final String SHAREDPREF_LASTCHECKDTM_GET_TREND_DETAILS = "last_checked_dtm_get_trend_details";
    public static final String SHAREDPREF_LASTCHECKDTM_GET_CIRCLE_LIST = "last_checked_dtm_get_circle_list";
    public static final String SHAREDPREF_LASTCHECKDTM_DELETE_CIRCLE = "last_checked_dtm_delete_circle";
    public static final String SHAREDPREF_LASTCHECKDTM_GET_CIRCLE_TRACKMETRIC_LIST = "last_checked_dtm_get_circle_trackmetric_list";
    public static final String SHAREDPREF_LASTCHECKDTM_ADD_CIRCLE_POST = "last_checked_dtm_add_circle_post";
    public static final String SHAREDPREF_PRIVACY_POLICY_URL = "privacy_policy_url";

    public static final String SHAREDPREF_LASTCHECKDTM_MEDICINE_LIST = "last_checked_dtm_get_medicine_list";

    public static final String SHAREDPREF_LASTCHECKDTM_CIRCLE_MEMBERLIST = "last_checked_dtm_circle_memberlist";

    public static final String SHAREDPREF_IDENTITYID = "IdentityId";
    public static final String SHAREDPREF_TOKEN = "Token";


    public static final String SHAREDPREF_IMAGEPATH = "imagepath";
    public static final String SHAREDPREF_UTM_CONTENT = "utm_content";


    public static final String ACTION_GET_USER_MEDICATION = "get_user_medication";
    public static final String ACTION_ADD_USER_MEDICATION = "add_user_medication";
    public static final String ACTION_UPDATE_USER_MEDICATION = "update_user_medication";
    public static final String ACTION_DELETE_USER_MEDICATION = "delete_user_medication";

    public static final String ACTION_GET_METRICS_LIST = "get_metric_list";
    public static final String ACTION_ADD_METRICS_LIST = "add_user_metric";
    public static final String ACTION_ADD_METRICS_RESEARCH = "add_metric_research";
    public static final String ACTION_USER_NOTE = "user_note";
    public static final String ACTION_ADD_USER_METRIC_DETAILS = "add_user_metric_detail";
    public static final String ACTION_GET_TOKEN_AWS = "api_get_openid_token_for_aws";
    public static final String ACTION_VERIFY_USER = "verify_user";
    public static final String ACTION_SEND_VERIFICATION_CODE = "send_verification_code";
    public static final String ACTION_SAVE_USERID_FORNOTIFICATION = "save_userid_fornotification";
    public static final String ACTION_GET_DASHBOARD_DETAILS = "get_dashboard_detail";

    public static final String ACTION_GET_COHORT_DETAILS = "get_cohort_details";

    public static final String ACTION_GET_MEMBER_RELATIONSHIP_LIST = "get_member_relationship_list";
    public static final String ACTION_Add_NEW_CIRCLE_MEMBER = "add_new_circle_member";

    public static final String ACTION_GET_CIRCLE_MEMBER_LIST = "get_circle_member_list";
    public static final String ACTION_ENABLE_DISABLE_MEMBER = "enable_disable_member";

    public static final String ACTION_GET_COMMENT_LIST = "get_comment_list";
    public static final String ACTION_GET_ADVICE = "get_advice";
    public static final String ACTION_GET_CIRCLE_POST_LIST = "get_circle_post_list";
    public static final String ACTION_DELETE_POST = "delete_post";
    public static final String ACTION_ADD_CIRCLE_LIKECOMMENTSHARE = "add_circle_likecommentshare";
    public static final String ACTION_DELETE_COMMENT = "delete_comment";
    public static final String ACTION_LOGOUT = "logout";

    public static final String ACTION_REGISTER_DEVICE = "register_device";

    //Intent actions for broadcastReceiver
    public static final String INTENT_GETCONDITION = "intent_getcondition";
    public static final String INTENT_ENABLEFIELDS = "intent_enablefields";
    public static final String INTENT_ENABLEDONEBTN = "intent_enabledonebtn";
    public static final String INTENT_CROPIMAGE = "intent_cropimage";
    public static final String INTENT_GETALLARTICLES = "intent_getallarticles";
    public static final String INTENT_GETARTICLES = "intent_getarticles";
    public static final String INTENT_GETPAPERS = "intent_getpapers";
    public static final String INTENT_GETBLOGS = "intent_getblogs";
    public static final String INTENT_GET_PRESCRIPTION = "intent_get_prescription";
    public static final String INTENT_GET_TREATMENT = "intent_get_treatment";
    public static final String INTENT_GET_NOTE_TITLE = "intent_get_note_title";
    public static final String INTENT_GET_USER_DATA_FROM_DB = "intent_get_user_data_from_db";
    public static final String INTENT_UPDATE_NOTE_LIST = "intent_update_note_list";
    public static final String INTENT_GET_DOCTOR_VISIT = "intent_get_doctor_visit";
    public static final String INTENT_UPLOAD_SUCCESS = "intent_upload_success";
    public static final String INTENT_DOWNLOAD_SUCCESS = "intent_download_success";
    public static final String INTENT_DOWNLOAD_SUCCESS_DISPLAY_PAGE = "intent_download_success_display_page";
    public static final String INTENT_DOWNLOAD_FAILED = "intent_download_failed";
    public static final String INTENT_DOWNLOAD_FAILED_DISPLAY_PAGE = "intent_download_failed_display_page";
    public static final String INTENT_FBDATATOSIGNUP = "intent_fbdatato_signup";
    public static final String INTENT_UPDATE_COUNT = "intent_UPDATE_COUNT";
    public static final String INTENT_VALIDATION_USER_FIELDS = "intent_validation_user_fields";
    public static final String INTENT_REFRESH_METRIC_DETAILS = "intent_refresh_metric_details";
    public static final String INTENT_REFRESH_TREND_DETAILS = "intent_refresh_trend_details";
    public static final String INTENT_REFRESH_RESEARCH_LIST = "intent_refresh_research_list";
    public static final String INTENT_REFRESH_RESEARCH_ALL_TAB = "intent_refresh_research_all_tab";
    public static final String INTENT_REFRESH_PROFILE = "intent_refresh_profile";
    public static final String INTENT_ON_MESSAGE_RECEIVED = "com.my.app.onMessageReceived";
    public static final String INTENT_ON_ADD_CIRCLE = "intent_on_add_circle";
    public static final String INTENT_ON_ADD_CIRCLE_POST = "intent_on_add_circle_post";
    public static final String INTENT_REFERAL = "intent_refferal";
    public static final String INTENT_REFERAL_SIGNUP = "intent_refferal_SignUp";

    public static final String INTENT_LOG_LEVEL = "intent_log_level";
    public static final String INTENT_LOG_VIEW_METRIC_LEVEL = "intent_log_view_metric_level";
    public static final String INTENT_LOG_ADD_METRIC_VALUE_LEVEL = "intent_log_add_metric_value_level";

    public static final String INTENT_ON_METER_TOUCH = "intent_on_meter_touch";
    public static final String INTENT_ON_SLIDER_TOUCH = "intent_on_slider_touch";
    public static final String INTENT_ON_SLIDER_WITH_EMOTION_TOUCH = "intent_on_slider_with_emotion_touch";
    public static final String INTENT_ON_CONTAINER_TOUCH = "intent_on_container_touch";


    public static final String SHAREDPREF_PROFILE_SECRET_KEY = "secret_key";
    public static String SHAREDPREF_METRICS_BADGECOUNT = "METRICS_BADGECOUNT";
    public static String SHAREDPREF_RESEARCH_BADGECOUNT = "research_badgecount";
    public static String SHAREDPREF_NOT_ACTIVE_BADGECOUNT = "notactive_badgecount";
    public static String SHAREDPREF_TOTAL_BADGECOUNT = "total_badgecount";
    public static String SHAREDPREF_CARE_CIRCLE_LIKEBADGECOUNT = "carecircle_likebadgecount";
    public static String SHAREDPREF_CARE_CIRCLE_COMMENTBADGECOUNT = "carecircle_commentbadgecount";
    public static String SHAREDPREF_CARE_CIRCLE_POSTBADGECOUNT = "carecircle_postbadgecount";


    public static final String SHAREDPREF_LASTCHECKDTM_CITYLIST = "last_checked_dtm_citylist";
    public static final String SHAREDPREF_LASTCHECKDTM_MEDICINELIST = "last_checked_dtm_medicinelist";

    public static final String SHAREDPREF_CITYLIST_STATUS = "citylist_status";
    public static final String SHAREDPREF_MEDICINELIST_STATUS = "medicinelist_status";

    // Log Track Health Level.
    public static final String SHAREDPREF_TRACK_HEALTH_START = "track_health_start";


    //Not Note Status
    public static final int NOTE_NOT_SYNC = 1;
    public static final int NOTE_SYNC = 0;
    public static final int NOTE_SYNC_UPDATE = 2;
    public static final int NOTE_DELETE = 3;

    //add mterics status
    public static final int Add_METRICS_NOT_SYNC = 0;
    public static final int Add_METRICS_SYNC = 1;

    public static String SHAREDPREF_NOTES_LIST_DTM = "metrics_list_dtm";
    public static String SHAREDPREF_NOTES_ISSEND = "nptes_IsSend";

    public static final String BUCKET_NAME = "devinphormd";   // UAT Encrypted Bucket Name


    //Request code for camera
    public static final int REQUEST_CODE_CAMERA = 100;


    public static int ALLTAB_OPEN_COUNT = 0;

    // Research Type;
    public static final int RESEARCH_ALL = 1;
    public static final int RESEARCH_ARTICLE = 2;
    public static final int RESEARCH_PAPER = 3;
    public static final int RESEARCH_BLOG = 4;


    // Doctor Visit Flag Insert,Update,Delete;
    public static final String EVENT_INSERT = "1";
    public static final String EVENT_UPDATE = "2";
    public static final String EVENT_DELETE = "3";


    // For Doctor Event Sync status
    public static final int EVENT_SYNC = 1;


    // Folder Name

    public static String FOLDER_NAME = ".Inphormd";


    public static String ConnectionTimeOutMessage = "Connection Timeout.";

    public static String SHAREDPREF_LOGOUT_DTM = "logout_dtm";
    public static String SHAREDPREF_ISFIRSTTIME = "isFirstTime";
    public static String SHAREDPREF_ISLOGOUT_AFTERSEVENDAYS = "logout_aftersevendyas";

    public static String SHAREDPREF_ISNotVerified = "isNotVerified";
    public static String SHAREDPREF_DOCTOR_VISIT_DTM = "doctor_visit_dtm";


    public static String SHAREDPREF_USERACTIVIIY_API_USERID = "useractivity_userid";
    public static String SHAREDPREF_USERACTIVIIY_DATE_API = "useractivity_date";

    public static final String SHAREDPREF_ISUSERPROFILEVISIBLE = "isUserProfileVisible";
    public static final String SHAREDPREF_ISHOSPDOCTORVISIBLE = "isHospDoctorVisible";
    public static final String SHAREDPREF_ISUPLOADPRESCRIPTIONIVISIBLE = "isUploadPrescriptionVisible";


    // Bundle
    public static final String BUNDLE_METRIC_MODEL = "MetricModel";
    public static final String JOURNAL_MODEL = "JournalModel";


    // MyJournal Fragment Constants
    public static final String IS_METER_TOUCH = "is_meter_touch";
    public static final String IS_METRIC_DELETED = "is_metric_deleted";
    // Type-1 for Medical
    //Type-2 for Journal
    // Type-0 for all
    public static final String METRIC_TYPE = "metric_type";

    public static String SHAREDPREF_DASH_ALERTMESSADE_ISINVISIBLE = "dash_alert_msg_isvisible";
    public static String INTENT_ON_GET_DASHBOARD_DETAILS = "intent_on_get_dashboard_details";


    public static final String IsFromCareCircle = "isfromcarecircle";


    public static final String PROFILE_PIC_PATH="profile_pic_path";

}
