import { gql } from "@apollo/client";
import { useQuery } from "@apollo/client/react";

const GET_USERS = gql`
    query GetAllUsers {
        getAllUsers {
            userId
            username
            email
            phoneNumber
        }
    }
`

function UsersList() {
    const {loading, error, data} = useQuery(GET_USERS);

    if (loading)
        return (
            <div className="flex justify-center items-center h-screen bg-gray-50">
                <div className="animate-spin rounded-full h-12 w-12 border-t-4 border-blue-600 border-solid">Loading Users...</div>
            </div>
        );

    if (error)
        return (
            <div className="min-h-screen bg-linear-to-b from-blue-50 to-indigo-100 py-12 px-6">
                <div className="bg-red-100 text-red-700 p-6 rounded-lg shadow-md">
                    <p className="font-semibold text-lg">Error loading users 😞</p>
                    <p className="text-sm mt-2">{error.message}</p>
                </div>
            </div>
        );

    return (

        <div className="min-h-screen bg-linear-to-b from-blue-50 to-indigo-100 py-12 px-6">
            <div className="text-center mb-10">
                <h1 className="text-4xl font-extrabold text-blue-700 drop-shadow-md">
                    👥 Registered Users
                </h1>
                <p className="text-gray-600 mt-2 text-sm">
                    A complete list of all active users in the system.
                </p>
            </div>

                <div className="max-w-6xl mx-auto grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                        {data.getAllUsers.map((user) => (
                    <div
                        key={user.userId}
                        className="bg-white shadow-lg rounded-2xl p-6 hover:shadow-2xl hover:scale-105 transform transition duration-300 ease-in-out border-t-4 border-blue-500"
                    >
                    <div className="flex justify-center mb-4">
                        <div className="bg-blue-100 text-blue-600 font-bold w-16 h-16 rounded-full flex items-center justify-center text-2xl uppercase shadow-sm">
                            {user.username[0]}
                        </div>
                    </div>

                    <div className="text-center">
                        <h2 className="text-xl font-semibold text-gray-800">
                            {user.username}
                        </h2>
                        <p className="text-gray-500 text-sm mt-1">{user.email}</p>
                        <p className="text-gray-600 text-sm mt-1">
                            📞 {user.phoneNumber}
                        </p>
                        </div>

                        <div className="mt-4 flex justify-center">
                            <button className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition duration-200 text-sm font-medium">
                                View Profile
                            </button>
                        </div>
                    </div>
                ))}
            </div>

            <div className="text-center mt-10 text-gray-600 text-sm">
                Total Users:{" "}
                <span className="font-bold text-blue-700">
                    {data.getAllUsers.length}
                </span>
            </div>
        </div>
    );
}

export default UsersList;