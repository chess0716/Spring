import axios from "./axios";

const AuthService = {
  login: async (data) => {
    try {
      const response = await axios.post("/auth/login", data);
      if (response.data.token) {
        localStorage.setItem("accessToken", response.data.token);
        localStorage.setItem("user", JSON.stringify(response.data.user));
        console.log("Token received:", response.data.token);
      } else {
        console.error("No access token received");
      }
      return response.data;
    } catch (error) {
      throw new Error("Login failed");
    }
  },

  signup: async (data) => {
    try {
      const response = await axios.post("/auth/join", data);
      return response.data;
    } catch (error) {
      throw new Error("Signup failed");
    }
  },

  fetchUserData: async () => {
    try {
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios.get("/members/me", {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      return response.data;
    } catch (error) {
      throw new Error("Failed to fetch user data");
    }
  },

  updateProfile: async (data) => {
    try {
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios.put("/members/update", data, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      return response.data;
    } catch (error) {
      throw new Error("Profile update failed");
    }
  },

  logout: async () => {
    try {
      const accessToken = localStorage.getItem("accessToken");
      await axios.post(
        "/auth/logout",
        {},
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );
      localStorage.removeItem("accessToken");
      localStorage.removeItem("user");
    } catch (error) {
      console.error("Failed to logout:", error);
      throw error;
    }
  },

  deleteAccount: async () => {
    try {
      const accessToken = localStorage.getItem("accessToken");
      await axios.delete("/members/me", {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      localStorage.removeItem("accessToken");
      localStorage.removeItem("user");
    } catch (error) {
      console.error("Failed to delete account:", error);
      throw error;
    }
  },
};

export default AuthService;
