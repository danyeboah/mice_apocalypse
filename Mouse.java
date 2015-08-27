public class Mouse
{
    //instance variables of the object mouse
    private int sex;
    private int allele1;
    private int allele2;
    
    public Mouse(int a1, int a2)
    {
        // creates an object mouse with a1 and a2 as its two alleles
        // it randomly assigns the create mouse a 1 or 0 (representing
        // male or female)
        double x = Math.random();
        if (x <= 0.5)
        {
            sex = 0;
        }
        else
        {
            sex = 1;
        }
        
        allele1 = a1;
        allele2 = a2;
    }
    
    // returns the sex of the mouse object
    public int sex()
    {
        return sex;
    }
    
    // returns the first allele of the mouse object
    public int whatallele1()
    {
        return allele1;
    }
    
    // returns the second allele of the mouse object
    public int whatallele2()
    {
        return allele2;
    }
    
    // mates the mouse object with another mouse object 'mate' passed in as input
    // it picks one allele at random from one mouse and another from the other mouse.
    // It combines these alleles to make a baby mouse
      public Mouse mating(Mouse mate)
    {
        int newa1;
        int newa2;
        double y = Math.random();
        double z = Math.random();
        if (y <= 0.5)
        {
            newa1 = allele1;
        }
        else
        {
            newa1 = allele2;
        }
        
        
        
        if (z <= 0.5)
        {
            newa2 = mate.whatallele1();
        }
        else
        {
            newa2 = mate.whatallele2();
        }
        
        Mouse baby = new Mouse(newa1, newa2);
        return baby;
    }
    
        // mating under t-haplotype condition
    public Mouse tmating(Mouse mate)
    {
        int newa1;
        int newa2;
        double y = Math.random();
        double z = Math.random();
        
        // if parent is a male and is heterozygous, pick t 90% of the time 
        // for the baby's first allele.
        if (sex == 0)
        {
            if ((allele1 == 2 && allele2 == 3) || (allele1 == 3 && allele2 == 2))
            {
                if (y <= 0.9)
                {
                    newa1 = 3;
                }
                else 
                    newa1 = 2;
            }
            else if (y <= 0.5)
            {
                newa1 = allele1;
            }
            else 
                newa1 = allele2;
        }
        
        else if (y <= 0.5)
        {
            newa1 = allele1;
        }
        else
        {
            newa1 = allele2;
        }
        
        
        if (mate.sex() == 0)
        {
            if ((mate.whatallele1() == 2 && mate.whatallele2() == 3) || (mate.whatallele1() == 3 && mate.whatallele2() == 2))
            {
                if (z <= 0.9)
                {
                    newa2 = 3;
                }
                else 
                    newa2 = 2;
            }
            else if (z <= 0.5)
            {
                newa2 = mate.whatallele1();
            }
            else 
                newa2 = mate.whatallele2();
        }
        
        else if (z <= 0.5)
        {
            newa2 = mate.whatallele1();
        }
        else
        {
            newa2 = mate.whatallele2();
        }
    
        Mouse baby = new Mouse(newa1, newa2);
        return baby;
    }
    
  
  
    
    public static Mouse[] ancestorgen(int n)
    {
        Mouse[] ancestor = new Mouse[n];
        
        // create 5000 mice that are randomly assigned male/female and
        // have random assignments of alleles.
        for (int i = 0; i < n; i = i + 1)
        {
            int a1;
            int a2;
            
            // randomly assign first allele as 2(A) or 3(a)
            double y = Math.random();
            if (y <= 0.5)
            {
                a1 = 2;
            }
            else 
            {
                a1 = 3;
            }
            
            //randomly assign second allele as 2(A) or 3(a)
            double z = Math.random();
            if (z <= 0.5)
            {
                a2 = 2;
            }
            else 
            {
                a2 = 3;
            }
            
            ancestor[i] = new Mouse(a1,a2);
        }
        return ancestor;
    }
    
    public static Mouse[] babygen(int n, Mouse[] ancestor)
    {
        Mouse[] baby = new Mouse[n];
        
        for (int j = 0; j < n; j = j + 1)
        {
            // randomly pick two mice
            int rand1 = (int)(Math.random() * n);
            int rand2 = (int)(Math.random() * n);
            
            // pick mice until their sexes are unequal
            while 
                (ancestor[rand1].sex() == ancestor[rand2].sex())
            {
                rand1 = (int)(Math.random() * n);
                rand2 = (int)(Math.random() * n);
            }
            
            // mate the chosen mice to create a baby mouse
            baby[j] = ancestor[rand1].mating(ancestor[rand2]);
        }
        return baby;
    }
    
    // calculates allele frequency of A for one generation of mice
    public static double pfreq(int n, Mouse[] ancestor)
    {
        double p = 0;
        double pfreq;
        
        for ( int i = 0; i < n; i = i + 1)
        {
            // increase A count if first allele = A
            if ((ancestor[i].whatallele1() == 2))
            {
                p = p + 1;
            }
            
            // increase A count if secpnd allele = A
            if ((ancestor[i].whatallele2() == 2))
            {
                p = p + 1;
            }
        }
        
        pfreq = p/ (2 * n);
        
        return pfreq;
    }
    
    // calculates allele frequency of a for one generation of mice
    public static double qfreq(int n, Mouse[] ancestor)
    {
        double q = 0;
        double qfreq;
        
        for ( int i = 0; i < n; i = i + 1)
        {
            // increase A count if first allele = A
            if ((ancestor[i].whatallele1() == 3))
            {
                q = q + 1;
            }
            
            // increase A count if secpnd allele = A
            if ((ancestor[i].whatallele2() == 3))
            {
                q = q + 1;
            }
        }
        
        qfreq = q/(2 * n);
        return qfreq;
    }
    
    
    public static double AAgenotype(int n, Mouse[] ancestor)
    {
        int alleleAA = 0;
        
        for ( int i = 0; i < n; i = i + 1)
        {
// increase AA count if first allele and second allele = A
            if ((ancestor[i].whatallele1() == 2) && (ancestor[i].whatallele2() == 2))
            {
                alleleAA = alleleAA + 1;
            }
        }
        return alleleAA;
    }
    
    
    
    public static double aagenotype(int n, Mouse[] ancestor)
    {
        int alleleaa = 0;
        
        for ( int i = 0; i < n; i = i + 1)
        {
// increase aa count if first allele and second allele = a
            if ((ancestor[i].whatallele1() == 3) && (ancestor[i].whatallele2() == 3))
            {
                alleleaa = alleleaa + 1;
            }
        }
        return alleleaa;
    }
    
    
    public static double Aagenotype(int n, Mouse[] ancestor)
    {
        int alleleAa = 0;
        
        for ( int i = 0; i < n; i = i + 1)
        {
// increase Aa count if first allele= A and second allele = a or vice versa
            if (((ancestor[i].whatallele1() == 2) && (ancestor[i].whatallele2() == 3)) ||
                ((ancestor[i].whatallele1() == 3) && (ancestor[i].whatallele2() == 2)))
            {
                alleleAa = alleleAa + 1;
            }
        }
        
        return alleleAa;
    }
    
    
    
    
    
    
    // calculates a ballpark generation at which the population reaches equilibrium
    // It takes in the p values in an array and calculates the standard deviation 
    // for a set m/5 generations at a time. If the p allele frequency of the set of generations has a standard deviation
    // below the threshold(0.02), then the beginning generation for such a set of values is the ballpark
    // generation at which the population reaches equilibrium
    // This method calculates the standard deviation for sets of values after the first
    // m/5 generations have passed, in other words, it does not look for equilibrium among the first
    // m/5 generations since populations usually start stable
    public static int equilibrium(double[] pratio, int m)
    {
        double psum;
        double average;
        double sumdifference;
        double std;
        
        int i ;
        int equilt = 0;
        int l = m / 5 ;
        for (i = m/10 ; i < m; i = i + l)
        {
            psum = pratio[i];
            for ( int j = i + 1; (j < i + l) && (j < m); j = j + 1)
            {
                psum = psum + pratio[j];
            }
            
            average = psum / l;
            
            sumdifference = 0;
            for (int r = i; (r < i + l) && (r < m); r = r + 1)
            {
                sumdifference = sumdifference + ((average - pratio[r]) * (average - pratio[r])) ;
            }
            
            std = Math.sqrt(sumdifference/ l);
            
            if (std < 0.02)
            {
                equilt = i;
                break;
            }
            
        }
        return equilt; 
    }
    
    // creates initial generation that has A:a ratio of 100%/0%
    public static Mouse[] ancestorgen1(int n)
    {
        Mouse[] ancestor = new Mouse[n];
        
        // create 5000 mice that are randomly assigned male/female and
        // but are all of the AA genotype.
        for (int i = 0; i < n; i = i + 1)
        {
            int a1 = 2;
            int a2 = 2;
            
            ancestor[i] = new Mouse(a1,a2);
        }
        return ancestor;
    }
    
    // creates initial mouse generation that has A:a ratio of 90%/10%
    public static Mouse[] ancestorgen2(int n)
    {
        Mouse[] ancestor = new Mouse[n];
        
        
        for (int i = 0; i < n; i = i + 1)
        {
            int a1;
            int a2;
            
            // assign first allele as A with a probability of 90% and 3(a) with 
            // a probability of 10%
            double y = Math.random();
            if (y <= 0.9)
            {
                a1 = 2;
            }
            else 
            {
                a1 = 3;
            }
            
            // assign first allele as A with a probability of 90% and 3(a) with 
            // a probability of 10%
            double z = Math.random();
            if (z <= 0.9)
            {
                a2 = 2;
            }
            else 
            {
                a2 = 3;
            }
            
            ancestor[i] = new Mouse(a1,a2);
        }
        return ancestor;
    }
    
    // creates initial mice where aa is lethal
    public static Mouse[] ancestorgenlethal(int n)
    {
        Mouse[] ancestor = new Mouse[n];
        Mouse ancestors;
        
        for (int i = 0; i < n; i = i + 1)
        {
            int a1;
            int a2;
            
            // randomly assign first allele as 2(A) or 3(a)
            double y = Math.random();
            if (y <= 0.5)
            {
                a1 = 2;
            }
            else 
            {
                a1 = 3;
            }
            
            //randomly assign second allele as 2(A) or 3(a)
            double z = Math.random();
            if (z <= 0.5)
            {
                a2 = 2;
            }
            else 
            {
                a2 = 3;
            }
            
            
            ancestors = new Mouse(a1,a2);
            
            // if the mouse has aa create another mouse
            while ( (ancestors.whatallele1() == 3) && (ancestors.whatallele2() == 3))
            {
            y = Math.random();
            if (y <= 0.5)
            {
                a1 = 2;
            }
            else 
            {
                a1 = 3;
            }
            
          
            z = Math.random();
            if (z <= 0.5)
            {
                a2 = 2;
            }
            else 
            {
                a2 = 3;
            }
            
            
            ancestors = new Mouse(a1,a2);
            }
            
            
            ancestor[i] = ancestors;
            
        }
        return ancestor;
    }
    
    
    // creates baby mice where aa is lethal
    public static Mouse[] babygenlethal(int n, Mouse[] ancestor)
    {
        Mouse[] baby = new Mouse[n];
        
        for (int j = 0; j < n; j = j + 1)
        {
            // randomly pick two mice
            int rand1 = (int)(Math.random() * n);
            int rand2 = (int)(Math.random() * n);
            
            // pick mice until their sexes are unequal
            while 
                (ancestor[rand1].sex() == ancestor[rand2].sex())
            {
                rand1 = (int)(Math.random() * n);
                rand2 = (int)(Math.random() * n);
            }
            
            
            
            
            // mate the chosen mice to create a baby mouse
            Mouse babies = ancestor[rand1].mating(ancestor[rand2]);
            
            // if the baby has aa, pick another pair and mate them
            while ( (babies.whatallele1() == 3) && (babies.whatallele2() == 3))
            {
                rand1 = (int)(Math.random() * n);
                rand2 = (int)(Math.random() * n);
                while 
                    (ancestor[rand1].sex() == ancestor[rand2].sex())
                {
                    rand1 = (int)(Math.random() * n);
                    rand2 = (int)(Math.random() * n);
                }
                
                babies = ancestor[rand1].mating(ancestor[rand2]);
            }
            
            
            
            baby[j] = babies;
            
        }
        return baby;
    }
    
    // creates baby mice where aa is lethal under t-haplotype conditions
    public static Mouse[] tbabygenlethal(int n, Mouse[] ancestor)
    {
        Mouse[] baby = new Mouse[n];
        
        for (int j = 0; j < n; j = j + 1)
        {
            // randomly pick two mice
            int rand1 = (int)(Math.random() * n);
            int rand2 = (int)(Math.random() * n);
            
            // pick mice until their sexes are unequal
            while 
                (ancestor[rand1].sex() == ancestor[rand2].sex())
            {
                rand1 = (int)(Math.random() * n);
                rand2 = (int)(Math.random() * n);
            }
            
            
            
            
            // mate the chosen mice taken into account t-haplotpye conditions to create a baby mouse
            Mouse babies = ancestor[rand1].tmating(ancestor[rand2]);
            
            // if the baby has aa, pick another pair and mate them
            while ( (babies.whatallele1() == 3) && (babies.whatallele2() == 3))
            {
                rand1 = (int)(Math.random() * n);
                rand2 = (int)(Math.random() * n);
                while 
                    (ancestor[rand1].sex() == ancestor[rand2].sex())
                {
                    rand1 = (int)(Math.random() * n);
                    rand2 = (int)(Math.random() * n);
                }
                
                babies = ancestor[rand1].tmating(ancestor[rand2]);
            }
            
            
            
            baby[j] = babies;
            
        }
        return baby;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main (String[] args)
    {
        // n refers to the number of mice in each generation
        // m refers to the number of generations
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        
        
        // create a mouse array of size n to store each parent mouse object 
        Mouse[] ancestor = new Mouse[n];
        
        
        // create a mouse array of size n to store each offspring mouse object
        Mouse[] baby = new Mouse[n];
        
        // create array to keep count of genotype frequencies
        int[] AAfreq = new int[m];
        int[] aafreq = new int[m];
        int[] Aafreq = new int[m];
        
        // create array to keep count of allele frequencies
        double[] pfreqcount = new double[m];
        double[] qfreqcount = new double[m];
        
        
        int t = 0;
        
        // create first generation and print out its allele and genotype frequencies
        ancestor = ancestorgenlethal(n);
        double pratio = pfreq(n, ancestor);
        double qratio = qfreq(n, ancestor);
        pfreqcount[0] = pratio;
        
        double AA = AAgenotype(n, ancestor);
        double aa = aagenotype(n, ancestor);
        double Aa = Aagenotype(n, ancestor);
        
        StdOut.printf("%6s","t");
        StdOut.printf("%6s", "  ");
        StdOut.printf("%6s","p");
        StdOut.printf("%6s","   ");
        StdOut.printf("%6s","q");
        StdOut.printf("%6s","   ");
        StdOut.printf("%6s","AA");
        StdOut.printf("%6s","   ");
        StdOut.printf("%6s","aa");
        StdOut.printf("%6s","   ");
        StdOut.printf("%6s\n","Aa");
        
        StdOut.printf("%6d",t);
        StdOut.printf("%6s"," ");
        StdOut.printf("%6.4f",pratio);
        StdOut.printf("%6s","           ");
        StdOut.printf("%6.4f",qratio);
        StdOut.printf("%6s","           ");
        StdOut.printf("%6.1f",AA);
        StdOut.printf("%6s","           ");
        StdOut.printf("%6.1f",aa);
        StdOut.printf("%6s","           ");
        StdOut.printf("%6.1f\n",Aa); 
        
        // create other generations and print out allele and genotype frequencies
        for ( int i = 1; i < m; i = i + 1)
        {
            t = i;
            baby = tbabygenlethal(n,ancestor);
            pratio = pfreq(n, baby);
            qratio = qfreq(n, baby);
            
            pfreqcount[i] = pratio;
            
            AA = AAgenotype(n, baby);
            aa = aagenotype(n, baby);
            Aa = Aagenotype(n, baby);
            
            StdOut.printf("%6d",t);
            StdOut.printf("%6s"," ");
            StdOut.printf("%6.4f",pratio);
            StdOut.printf("%6s","           ");
            StdOut.printf("%6.4f",qratio);
            StdOut.printf("%6s","           ");
            StdOut.printf("%6.1f",AA);
            StdOut.printf("%6s","           ");
            StdOut.printf("%6.1f",aa);
            StdOut.printf("%6s","           ");
            StdOut.printf("%6.1f\n",Aa);   
            
            // make babies the new parents
            ancestor = baby;
        }
        
        // calls on the static function 'equilibrium'that checks to see if there 
        // is an equilibrium 
        // and returns the value of the generation at which equilibrium starts
        int equili = equilibrium(pfreqcount,m);
        System.out.print("Equilibrium is" + " " + equili);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}








