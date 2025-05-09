**Client related File**

The clientRelated package manages all client and leasing functionality within the Vehicle Management System. It includes the Client class to represent customers and the Lease class to associate clients 
with vehicles. The ClientManager handles operations such as adding, editing, and deleting clients, while preventing deletions if active leases exist. The LeaseManager oversees the leasing process — 
assigning and returning vehicles, and displaying lease records. Together, these classes enable full tracking of which clients have leased which vehicles, enforcing data integrity and business rules.
