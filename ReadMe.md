OverView

What is Severless

Serverless definition
Serverless computing is a method of providing backend services on an as-used basis. Servers are still used, but a company that gets backend services from a serverless vendor is charged based on usage, not a fixed amount of bandwidth or number of servers.
https://www.cloudflare.com/learning/serverless/what-is-serverless/

Why Severless


Pros and Cons with severless
1. LOWER COSTS
With serverless, you only pay for what you use—there are no hardware costs and no costs when your services are not in use. Reduced cost is one of the main advantages of going serverless. If your services aren’t heavily used all of the time, then the  ‘pay-as-you-go’ model gives you optimal resource utilization without paying for idle server time.

2. FEWER THINGS TO WORRY ABOUT
You are relieved from worrying about if the latest security has been applied. Because your servers are now in the hands of a third-party service provider, you no longer need to be concerned with keeping up with patches and bug fixes.

3. ENHANCED SCALABILITY
You may want to build a viral app like Instagram or Uber, but would you risk provisioning infrastructure just in case? With serverless architecture, you don’t have to think twice about it because of its ability to automatically scale with traffic volumes. Scaling also depends on the location of users and their network connection. Serverless providers have points of presence around all users, which diminishes delays and allows apps to perform as they should, regardless of your geographic location.

4. MORE FOCUS ON USER EXPERIENCE
Your users don’t care about infrastructure; they care about features. With server maintenance out of mind, companies can dedicate more time and effort to improving the customer-facing elements. A quality UX design is essential if you want to keep users engaged with your app.

Serverless computing allows developers to build apps without the headache of managing infrastructure. More specially, it enables them to write in serverless code without having to:

Provision a server
Ensure its functionality
Create test environments on a server
Maintain server uptime

1. VENDOR LOCK-IN
You’re playing by their rules now. Once you give control of your servers to third-party providers, you lose control over the hardware, the run times, and updates. This can create issues in consistency and limit the resources you have available.

Additionally, when you commit to a service provider, you are in it for the long-run. If you build your application on one serverless infrastructure, and then you want to transition to another vendor, they don’t make it easy to switch. It is a very difficult process, and you may need to re-engineer your application if you wish to do so. Plus, there is a risk that a vendor will change its pricing or service terms or even stop offering serverless options.

2. UNSUITABLE FOR LONG-TERM TASKS
Serverless is great for short-term or real-time processes like sending out emails. However, for longer duration tasks, where functions are running constantly, you may end up paying more for compute time than when paying for a reserved instance. A task such as uploading large video files would require additional functions to be called on.

man sending email
For example, Lambda gives you five minutes to complete a task. If it takes longer, you must call another function, and so on until the task is complete. Lambda also imposes a limit on how many simultaneous functions can be running, so if you execute too many functions you’ll “denial of service” (DoS) your production applications.

3. COLD STARTS
You only pay for what you use, but if you don’t use your function often, you will pay with a dramatic performance penalty. Hosted functions suffer from a cold-start penalty and can be very slow the first time they are called in a while.

However, you can attempt to minimize cold starts by keeping your functions small and precise, as cold starts increase with code size and memory. This will help keep your functions warm (but the drawback is that it is complicated and inefficient to manage numerous small functions).

4. COMPLEXITY
The learning curve in serverless application is a steep one. Units of integration with serverless are a lot smaller than with other architectures. This requires extra time to go into organizing the functions so they work in line with your data. There can also be problems with deployment and versioning.

AWS functions are time-restricted, allowing you a maximum of five minutes. If you have a task that requires a large amount of data that could exceed these runtime limits, a lot of effort will have to go into rewriting the code in another architecture. You may need to deploy a separate piece of code for every function in your entire logical application.

There are frameworks that allow you to deploy a collection of AWS resources, but it’s more difficult to update multiple functions than to update a monolithic architecture. However, if you want to migrate your application over to serverless you need to tackle the complicated task of splitting your monolithic application to microservices.


From a business perspective, the disadvantages of vendor lock-in and loss of control could be damaging. Even though it may be cost-effective, it is also unpredictable because the number of executions is not predefined.

For developers, serverless is a relatively new technology. It can be very complex and testing becomes tricky. If not developed correctly, companies using serverless can lose customers due to poor performance.
https://www.bmc.com/blogs/serverless-computing/
https://learntocodewith.me/posts/serverless-architecture/

project goals 
To see the difference between serverless and a traditional server especially for smaller business who might not be able to afford a server or do not need 1 running 24/7

How to run on your pc

1.Clone project down or do the nessessary step to download and unzip the project

2.Open  the client folder in visual studio code. This Client folder is inside the Final-Year-Project-2021-22 and contains Client folder

3.Open up a terimal in visual studio code

4. Do a npm install and wait to install
 
5. Do a npm start and wait for it to start up
 
6. Now you should see the home on local host 3000

Please be of wear, That sometimes the git clone changes the captail/Noncaptail at the start of the js name e.g home to Home, The fix for this is simpiliy chaning the front back to the original './Home', './create', './login', './signUp', './user', './logOut', './tournaments', './oneTouramentJoin', './inTournament', './tournamentLoaderPlayerFlase', ./tournamentLoaderPlayerTrue
