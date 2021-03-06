package eu.jnksoftware.discountfinderandroid;


/*public class TestUserPreferences extends TestCase{
        @Test
        public void testPostId() throws IOException

        {
            IuserService s = ApiUtils.getMockUserService();
            DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
            discountPreferencesRequest.setPrice("50");
            discountPreferencesRequest.setCategory("1");
            discountPreferencesRequest.setTags("Sample,Demo,App,Data");
            String auth="Bearer mock_access";
            DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
            assertEquals("1", discountPreferencesPostResponse.getId().toString());
        }



    @Test
    public void testPostCategory() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesPostResponse.getCategory().toString());
    }
    @Test
    public void testPostPrice() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("50", discountPreferencesPostResponse.getPrice().toString());
    }
    @Test
    public void testPostTags() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setTags("Sample,Demo,App,Data");
        String auth="Bearer mock_access";
        DiscountPreferencesPostResponse discountPreferencesPostResponse  = s.postDiscountPreferences(discountPreferencesRequest,auth).execute().body();
        assertEquals("Sample,Demo,App,Data", discountPreferencesPostResponse.getTags().toString());
    }
    @Test
    public void testGetid() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
       List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("37", discountPreferencesResponse.get(0).getId().toString());
    }
    @Test
    public void testGetCategory() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("1", discountPreferencesResponse.get(0).getCategory().toString());
    }
    @Test
    public void testGetUserid() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("30", discountPreferencesResponse.get(0).getUserId().toString());
    }
    @Test
    public void testGetPrice() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("50.0", Float.toString( discountPreferencesResponse.get(0).getPrice()));
    }

    @Test
    public void testGetTag() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("Sample, Demo, App, Data", discountPreferencesResponse.get(0).getTags().toString());
    }

    @Test
    public void testGetCategoryTitle() throws IOException

    {
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        List<DiscountPreferencesResponse> discountPreferencesResponse=  s.getDiscountsPreference(auth).execute().body();
        assertEquals("Computer", discountPreferencesResponse.get(0).getCategoryTitle().toString());
    }

    @Test
    public void testPutcategory() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesResponse.getCategory().toString());
    }
    @Test
    public void testPutPrice() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("50.0",Float.toString(discountPreferencesResponse.getPrice()));
    }
    @Test
    public void testPutTags() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("Demo,App,Data", discountPreferencesResponse.getTags().toString());
    }
    @Test
    public void testPutId() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesResponse.getId().toString());
    }
    @Test
    public void testPutUserId() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("1", discountPreferencesResponse.getUserId().toString());
    }
    /*@Test
    public void testPutImage() throws IOException

    {
        DiscountPreferencesRequest discountPreferencesRequest=new DiscountPreferencesRequest();
        discountPreferencesRequest.setCategory("1");
        discountPreferencesRequest.setPrice("50");
        discountPreferencesRequest.setTags("Demo,App,Data");
        IuserService s = ApiUtils.getMockUserService();
        String auth="Bearer mock_access";
        int id=1;
        DiscountPreferencesResponse discountPreferencesResponse= s.putDiscountPreferences(id,discountPreferencesRequest,auth).execute().body();
        assertEquals("http://img.youtube.com/", discountPreferencesResponse.getImage().toString());
    }






}*/
