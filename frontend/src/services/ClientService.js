import httpClient from "../interceptor/Interceptor";

class ClientService {

    async getCardBalance() {
        try {
            const response = await httpClient.get(`/credit-card/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching credit cards:', error);
            throw error;
        }
    };

    async getFinancialGoal() {
        try {
            const response = await httpClient.get(`/financial-goal/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching financial goal:', error);
            throw error;
        }
    };

    async getTransactions() {
        try {
            const response = await httpClient.get(`/transaction/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching transaction:', error);
            throw error;
        }
    };

    async addFinancialGoal(goal) {
        try {
            const response = await httpClient.post(`/financial-goal/${localStorage.getItem('id')}`, goal);
            return response.data;
        } catch (error) {
            console.error('Error adding new financial goal:', error);
            throw error;
        }
    };

    async addTransaction(transaction) {
        try {
            const response = await httpClient.post(`/transaction/${localStorage.getItem('id')}`, transaction);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error adding transaction:', error);
            throw error;
        }
    };

    async getReports() {
        try {
            const response = await httpClient.get(`/report/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching report:', error);
            throw error;
        }
    };

    async addReport(report) {
        try {
            const response = await httpClient.post(`/report/${localStorage.getItem('id')}`, report);
            return response.data;
        } catch (error) {
            console.error('Error adding report:', error);
            throw error;
        }
    };

    async getBudget() {
        try {
            const response = await httpClient.get(`/budget/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching report:', error);
            throw error;
        }
    };

    async addBudget(budget) {
        try {
            const response = await httpClient.post(`/budget/${localStorage.getItem('id')}`, budget);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error adding report:', error);
            throw error;
        }
    };

    async getHousehold() {
        try {
            const response = await httpClient.get(`/household/${localStorage.getItem('id')}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching household:', error);
            throw error;
        }
    };

    async addHousehold(household) {
        try {
            const response = await httpClient.post(`/household/${localStorage.getItem('id')}`, household);
            console.log(response)
            return response.data;
        } catch (error) {
            console.error('Error adding household:', error);
        }
    };
      
    async getFinancialGoalCalculation(value) {
        try {
            console.log()
            const response = await httpClient.post(`/financial-goal/calculator/${localStorage.getItem('id')}/${value}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching report:', error);
            throw error;
        }
    };
}

const clientService = new ClientService();
export default clientService;
