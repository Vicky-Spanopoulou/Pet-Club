package cb13.project.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000; 
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String KAYATE = "Kayate, LLC";
    public static final String KAYATE_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS ={"/user/login", "/user/register","/user/verify/{vc}","/ads/getAll"
            ,"/ads/getById/{id}","/business/getAllPublished","/business/get/{id}","/business/avgRating","/image/files/**",
            "/user/getUserByAd/{id}","/categories/pet_category/getAll","/categories/pet_sex/getAll","/categories/adCategories/getAll"
            ,"/categories/city/getAll ","/categories/city/getAll","/categories/businessCat/getAll",
            "/user/getUserByBusiness/{id}","/rating/get/**","/getUserByUsername/**","/email/**","/chat/**"};





}
